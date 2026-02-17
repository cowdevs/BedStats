package com.example.hypixelstats

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TAG = "DetailActivity"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var statistics: Statistics

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

        setupModeSpinner()

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
        binding.imageViewPlayerSkin.load(url)
    }

    private fun loadHypixelPlayerData(uuid: String) {
        val hypixelService = RetrofitHelper.getInstanceHypixelAPI().create(HypixelService::class.java)
        val hypixelCall = hypixelService.getHypixelPlayerData(uuid)
        hypixelCall.enqueue(object : Callback<PlayerData> {
            override fun onResponse(call: Call<PlayerData>, response: Response<PlayerData>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    statistics = Statistics.from(body)
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
        binding.textViewPlayerName.text = statistics.displayName
        binding.textViewLevel.text = statistics.level.toString()

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
        val mode = when (index) {
            0 -> statistics.overall
            1 -> statistics.solo
            2 -> statistics.doubles
            3 -> statistics.threes
            4 -> statistics.fours
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