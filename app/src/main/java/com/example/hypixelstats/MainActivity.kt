package com.example.hypixelstats

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hypixelstats.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PLAYER = "com.example.hypixelstats.PLAYER"
    }

    private lateinit var binding: ActivityMainBinding

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
        val mojangService = RetrofitHelper.getInstanceMojangAPI().create(MojangService::class.java)
        val mojangCall = mojangService.queryUsername(name)
        mojangCall.enqueue(object : Callback<Player> {
            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                val player = response.body()
                if (response.isSuccessful && player != null) {
                    handlePlayerResponse(player)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load player", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Player>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handlePlayerResponse(player: Player) {
        binding.buttonMainSearch.isEnabled = true
        goToDetailPage(player)
    }

    private fun goToDetailPage(player: Player) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_PLAYER, player)
        startActivity(intent)
    }
}