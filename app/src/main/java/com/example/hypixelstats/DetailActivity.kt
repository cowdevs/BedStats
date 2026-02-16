package com.example.hypixelstats

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import com.example.hypixelstats.databinding.ActivityDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TAG = "DetailActivity"
        const val BASE_URL_STARLIGHT_API = "https://starlightskins.lunareclipse.studio/render/"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val data = loadTestJSON()
//        Log.d(TAG, data.toString())
//        Log.d(TAG, data.player.stats.bedwars.foursFinalDeaths.toString())

        val player = intent.getParcelableExtra(MainActivity.EXTRA_PLAYER, Player::class.java)
        if (player != null) {
            binding.imageViewPlayerSkin.load(BASE_URL_STARLIGHT_API + "default/${player.id}/bust")
            val hypixelService = RetrofitHelper.getInstanceHypixelAPI().create(HypixelService::class.java)
            val hypixelCall = hypixelService.getHypixelPlayerData(player.id)
            hypixelCall.enqueue(object : Callback<PlayerData> {
                override fun onResponse(
                    call: Call<PlayerData?>, response: Response<PlayerData?>
                ) {
                    val data = response.body()
                    if (data != null) {
                        Log.d(TAG, "onResponse: ${data.player.stats.bedwars.doublesFinalKills}")
                    }
                }

                override fun onFailure(call: Call<PlayerData?>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
        } else {
            Toast.makeText(this, "Error retrieving player data", Toast.LENGTH_SHORT).show()
            finish()
        }


    }

    private fun loadTestJSON(): PlayerData {
        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.test_response)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<PlayerData>() {}.type
        return gson.fromJson(jsonString, type)
    }

}