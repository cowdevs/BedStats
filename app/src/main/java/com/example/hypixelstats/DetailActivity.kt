package com.example.hypixelstats

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import com.example.hypixelstats.databinding.ActivityDetailBinding
import com.example.hypixelstats.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.round

class DetailActivity : AppCompatActivity() {
    companion object {
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


        val player = intent.getParcelableExtra(MainActivity.EXTRA_PLAYER, Player::class.java)
        if (player != null) {
            Toast.makeText(this, player.id, Toast.LENGTH_SHORT).show()
            binding.imageView.load(BASE_URL_STARLIGHT_API + "default/${player.id}/bust")
        } else {
            Toast.makeText(this, "Error retrieving player data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}