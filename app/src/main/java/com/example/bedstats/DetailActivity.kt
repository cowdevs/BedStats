package com.example.bedstats

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bedstats.databinding.ActivityDetailBinding
import kotlin.math.round

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var stats: Statistics

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

        val statistics = intent.getParcelableExtra(MainActivity.EXTRA_STATISTICS, Statistics::class.java)
        val skinBitmapBytes = intent.getByteArrayExtra(MainActivity.EXTRA_SKIN_BITMAP)

        if (statistics != null) {
            stats = statistics
            if (skinBitmapBytes != null) {
                val bitmap = BitmapFactory.decodeByteArray(skinBitmapBytes, 0, skinBitmapBytes.size)
                binding.imageViewPlayerSkin.setImageBitmap(bitmap)
            }
            setupModeSpinner()
            handlePlayerDataResponse()
        } else {
            Toast.makeText(this, "Error retrieving player data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun handlePlayerDataResponse() {
        binding.textViewPlayerName.text = stats.displayName
        binding.textViewLevel.text = stats.level.toString()

        binding.textViewIron.text = stats.iron.toString()
        binding.textViewGold.text = stats.gold.toString()
        binding.textViewDiamonds.text = stats.diamonds.toString()
        binding.textViewEmeralds.text = stats.emeralds.toString()

        showStatsForMode("Overall")
    }

    private fun setupModeSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.mode_options, R.layout.item_spinner)
        adapter.setDropDownViewResource(R.layout.dropdown_item_spinner)
        binding.spinnerModeSelect.adapter = adapter

        binding.spinnerModeSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selected = parent.getItemAtPosition(position).toString()
                showStatsForMode(selected)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun showStatsForMode(selected: String) {
        val mode = when (selected) {
            "Overall" -> stats.overall
            "Core" -> stats.core
            "Solo" -> stats.solo
            "Doubles" -> stats.doubles
            "Threes" -> stats.threes
            "Fours" -> stats.fours
            "4v4" -> stats.fours2
            "Armed" -> stats.armed
            "Castle" -> stats.castle
            "Lucky" -> stats.lucky
            "Rush" -> stats.rush
            "Swap" -> stats.swap
            "Ultimate" -> stats.ultimate
            "Underworld" -> stats.underworld
            "Voidless" -> stats.voidless
            "One Block" -> stats.oneblock
            else -> return
        }

        binding.textViewKills.text = mode.kills.toString()
        binding.textViewDeaths.text = mode.deaths.toString()
        binding.textViewFinalKills.text = mode.finalKills.toString()
        binding.textViewFinalDeaths.text = mode.finalDeaths.toString()
        binding.textViewWins.text = mode.wins.toString()
        binding.textViewLosses.text = mode.losses.toString()
        binding.textViewBedsBroken.text = mode.bedsBroken.toString()
        binding.textViewBedsLost.text = mode.bedsLost.toString()

        val kdrRounded = round(mode.kdr * 100) / 100.0
        val fkdrRounded = round(mode.fkdr * 100) / 100.0
        val wlrRounded = round(mode.wlr * 100) / 100.0
        val bblrRounded = round(mode.bblr * 100) / 100.0

        binding.textViewKdr.text = kdrRounded.toString()
        binding.textViewFkdr.text = fkdrRounded.toString()
        binding.textViewWlr.text = wlrRounded.toString()
        binding.textViewBblr.text = bblrRounded.toString()
    }
}