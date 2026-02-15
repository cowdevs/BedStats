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
        const val EXTRA_PLAYER = "playeyyeryeyryerrrrrr"
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
            binding.buttonMainSearch.isEnabled = false

            val mojangService = RetrofitHelper.getInstanceMojangAPI().create(MojangService::class.java)
            val mojangCall = mojangService.queryUsername(searchPlayer)
            mojangCall.enqueue(object : Callback<Player> {
                override fun onResponse(call: Call<Player?>, response: Response<Player?>) {
                    binding.buttonMainSearch.isEnabled = true
                    if (!response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Request failed (${response.code()})", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val player = response.body()
                    if (player != null) {
                        goToDetailPage(player)
                    } else {
                        Toast.makeText(this@MainActivity, "Player not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Player?>, t: Throwable) {
                    binding.buttonMainSearch.isEnabled = true
                    Toast.makeText(this@MainActivity, "Network error: ${t.localizedMessage ?: "unknown"}", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }

    private fun goToDetailPage(player: Player) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_PLAYER, player)
        startActivity(intent)
    }
}