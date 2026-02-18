package com.example.hypixelstats

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hypixelstats.databinding.ActivityDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TAG = "DetailActivity"
    }

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

//        val tagFormatter = TagFormatter()
//        val tagComponents = tagFormatter.calcTag(data)
//        val tagString = tagComponents.joinToString("") { "§${it.colorCode}${it.text}" }
//        binding.textViewPlayerName.text = tagString + data.player.displayName


        val player = intent.getParcelableExtra(MainActivity.EXTRA_PLAYER, Player::class.java)
        if (player != null) {
            loadSkin(player.id)
            loadHypixelPlayerData(player.id)
        } else {
            Toast.makeText(this, "Error retrieving player data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadSkin(uuid: String) {
        val url = "https://starlightskins.lunareclipse.studio/render/default/$uuid/bust"
        Picasso.get().load(url).into(binding.imageViewPlayerSkin)
    }

    private fun loadHypixelPlayerData(uuid: String) {
        val hypixelService = RetrofitHelper.getInstanceHypixelAPI().create(HypixelService::class.java)
        val hypixelCall = hypixelService.getHypixelPlayerData(uuid)
        hypixelCall.enqueue(object : Callback<PlayerData> {
            override fun onResponse(call: Call<PlayerData>, response: Response<PlayerData>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    stats = Statistics.from(body)
                    setupModeSpinner()
                    handlePlayerDataResponse()
                } else {
                    Toast.makeText(this@DetailActivity, "Failed to load player stats", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlayerData>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handlePlayerDataResponse() {
        binding.textViewPlayerName.text = stats.displayName
        binding.textViewLevel.text = stats.level.toString()

        binding.textViewIron.text = stats.iron.toString()
        binding.textViewGold.text = stats.gold.toString()
        binding.textViewDiamonds.text = stats.diamonds.toString()
        binding.textViewEmeralds.text = stats.emeralds.toString()

        showStatsForMode(0)
    }

    private fun setupModeSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.mode_options, R.layout.item_spinner)
        adapter.setDropDownViewResource(R.layout.dropdown_item_spinner)
        binding.spinnerModeSelect.adapter = adapter

        binding.spinnerModeSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                showStatsForMode(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun showStatsForMode(index: Int) {
        val modes = listOf(
            stats.overall,
            stats.solo,
            stats.doubles,
            stats.threes,
            stats.fours,
            stats.core,
            stats.fours2,
            stats.rush,
            stats.ultimate,
            stats.castle,
            stats.voidless,
            stats.armed,
            stats.lucky,
            stats.swap,
            stats.oneblock
        )

        if (index !in 0..modes.size - 1) {
            Toast.makeText(this, "Error selecting mode", Toast.LENGTH_SHORT).show()
            return
        }

        val mode = modes[index]

        binding.textViewKills.text = mode.kills.toString()
        binding.textViewDeaths.text = mode.deaths.toString()
        binding.textViewFinalKills.text = mode.finalKills.toString()
        binding.textViewFinalDeaths.text = mode.finalDeaths.toString()
        binding.textViewWins.text = mode.wins.toString()
        binding.textViewLosses.text = mode.losses.toString()
        binding.textViewBedsBroken.text = mode.bedsBroken.toString()
        binding.textViewBedsLost.text = mode.bedsLost.toString()

        binding.textViewKdr.text = String.format(Locale.US, "%.2f", mode.kdr)
        binding.textViewFkdr.text = String.format(Locale.US, "%.2f", mode.fkdr)
        binding.textViewWlr.text = String.format(Locale.US, "%.2f", mode.wlr)
        binding.textViewBblr.text = String.format(Locale.US, "%.2f", mode.bblr)
    }

    private fun loadTestJSON(): PlayerData {
        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.test_recapp_)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<PlayerData>() {}.type
        return gson.fromJson(jsonString, type)
    }
}