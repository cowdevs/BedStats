package com.example.bedstats

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bedstats.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val EXTRA_STATISTICS = "com.example.hypixelstats.STATISTICS"
        const val EXTRA_SKIN_BITMAP = "com.example.hypixelstats.SKIN_BITMAP"
    }

    private lateinit var binding: ActivityMainBinding


    // Loading flags
    private var isLoading = false
    private var loadedStatistics: Statistics? = null
    private var loadedSkinBitmap: Bitmap? = null
    private var skinLoadingComplete = false
    private var statsLoadingComplete = false

    private var skinTarget: Target? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonMainSearch.setOnClickListener {
            val searchPlayer = binding.editTextMainSearchPlayer.text.toString().trim()
            if (!searchPlayer.isEmpty()) {
                binding.buttonMainSearch.isEnabled = false
                loadMinecraftPlayer(searchPlayer)
            }
        }
    }

    private fun loadMinecraftPlayer(name: String) {
        showLoading()
        val mojangService = RetrofitHelper.getInstanceMojangAPI().create(MojangService::class.java)
        val mojangCall = mojangService.queryUsername(name)
        mojangCall.enqueue(object : Callback<Player> {
            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                val player = response.body()
                if (player == null) {
                    hideLoading()
                    binding.buttonMainSearch.isEnabled = true
                    Log.d(TAG, "Mojang API: Nonexistent player '$name'")
                    Toast.makeText(this@MainActivity, "Player not found", Toast.LENGTH_SHORT).show()
                    return
                }

                if (response.isSuccessful) {
                    handleMojangCallResponse(player)
                } else {
                    hideLoading()
                    binding.buttonMainSearch.isEnabled = true
                    Log.e(TAG, "Mojang API error: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@MainActivity, "Failed to load player", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Player>, t: Throwable) {
                hideLoading()
                binding.buttonMainSearch.isEnabled = true
                Log.e(TAG, "Mojang API network error: ${t.message}", t)
                Toast.makeText(this@MainActivity, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleMojangCallResponse(player: Player) {
        isLoading = true
        loadedStatistics = null
        loadedSkinBitmap = null
        skinLoadingComplete = false
        statsLoadingComplete = false

        loadSkinBitmap(player.id)
        loadHypixelPlayerData(player.id)
    }

    private fun loadSkinBitmap(uuid: String) {
        val url = "https://starlightskins.lunareclipse.studio/render/default/$uuid/bust"
        skinTarget = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                loadedSkinBitmap = bitmap
                skinLoadingComplete = true
                checkLoadingComplete()
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                loadedSkinBitmap = null
                skinLoadingComplete = true
                checkLoadingComplete()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }
        Picasso.get().load(url).into(skinTarget!!)
    }

    private fun loadHypixelPlayerData(uuid: String) {
        val hypixelService = RetrofitHelper.getInstanceHypixelAPI().create(HypixelService::class.java)
        val hypixelCall = hypixelService.getHypixelPlayerData(uuid)
        hypixelCall.enqueue(object : Callback<PlayerData> {
            override fun onResponse(call: Call<PlayerData>, response: Response<PlayerData>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    loadedStatistics = Statistics.from(body)
                    if (loadedStatistics == null) {
                        isLoading = false
                        hideLoading()
                        binding.buttonMainSearch.isEnabled = true
                        Log.d(TAG, "Hypixel API: UUID '$uuid' has no Bedwars data available")
                        Toast.makeText(this@MainActivity, "Player has not played Bedwars", Toast.LENGTH_SHORT).show()
                        return
                    }

                    statsLoadingComplete = true
                    checkLoadingComplete()
                } else {
                    isLoading = false
                    hideLoading()
                    binding.buttonMainSearch.isEnabled = true
                    Log.e(TAG, "Hypixel API error: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@MainActivity, "Failed to load player stats", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlayerData>, t: Throwable) {
                isLoading = false
                hideLoading()
                binding.buttonMainSearch.isEnabled = true
                Log.e(TAG, "Hypixel API network error: ${t.message}", t)
                Toast.makeText(this@MainActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun checkLoadingComplete() {
        val stats = loadedStatistics

        if (skinLoadingComplete && statsLoadingComplete && stats != null && isLoading) {
            isLoading = false
            hideLoading()
            binding.buttonMainSearch.isEnabled = true
            goToDetailPage(stats, loadedSkinBitmap)
        }
    }

    private fun showLoading() {
        binding.progressBarMainLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBarMainLoading.visibility = View.GONE
    }

    private fun goToDetailPage(statistics: Statistics, skinBitmap: Bitmap?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_STATISTICS, statistics)

        if (skinBitmap != null) {
            val stream = ByteArrayOutputStream()
            skinBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            intent.putExtra(EXTRA_SKIN_BITMAP, stream.toByteArray())
        }

        startActivity(intent)
    }
}