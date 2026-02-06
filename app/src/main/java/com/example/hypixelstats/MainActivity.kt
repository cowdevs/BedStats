package com.example.hypixelstats

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
            val searchPlayer = binding.editTextMainSearchPlayer.text.toString()
            if (searchPlayer.isNotEmpty()) {
                val mojangService = RetrofitHelper.getInstance().create(MojangService::class.java)
                val mojangCall = mojangService.queryUsername(searchPlayer)
                mojangCall.enqueue(object : Callback<Player> {
                    override fun onResponse(call: Call<Player?>, response: Response<Player?>) {
                        val body = response.body()
                        if (body != null) {
                            val player = body.id
                            Toast.makeText(this@MainActivity, player, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "Player not found", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Player?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }


    }
}