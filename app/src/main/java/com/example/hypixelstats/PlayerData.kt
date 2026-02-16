package com.example.hypixelstats

import com.google.gson.annotations.SerializedName

data class PlayerData(
    val player: HypixelPlayer,
) {
    data class HypixelPlayer(
        @SerializedName("displayname") val displayName: String,
        @SerializedName("playername") val playerName: String,
        val stats: Statistics,
        @SerializedName("networkExp") val networkExperience: Double,
        val karma: Int,
        @SerializedName("newPackageRank") val rank: String,
    )

    data class Statistics(
        @SerializedName("Bedwars") val bedwars: BedwarsStatistics
    ) {
        data class BedwarsStatistics(
            // Overall
            @SerializedName("wins_bedwars") val overallWins: Int,
            @SerializedName("losses_bedwars") val overallLosses: Int,
            @SerializedName("final_kills_bedwars") val overallFinalKills: Int,
            @SerializedName("final_deaths_bedwars") val overallFinalDeaths: Int,
            @SerializedName("kills_bedwars") val overallKills: Int,
            @SerializedName("deaths_bedwars") val overallDeaths: Int,
            @SerializedName("beds_broken_bedwars") val overallBedsBroken: Int,
            @SerializedName("beds_lost_bedwars") val overallBedsLost: Int,

            // Solo
            @SerializedName("eight_one_wins_bedwars") val soloWins: Int,
            @SerializedName("eight_one_losses_bedwars") val soloLosses: Int,
            @SerializedName("eight_one_final_kills_bedwars") val soloFinalKills: Int,
            @SerializedName("eight_one_final_deaths_bedwars") val soloFinalDeaths: Int,
            @SerializedName("eight_one_kills_bedwars") val soloKills: Int,
            @SerializedName("eight_one_deaths_bedwars") val soloDeaths: Int,
            @SerializedName("eight_one_beds_broken_bedwars") val soloBedsBroken: Int,
            @SerializedName("eight_one_beds_lost_bedwars") val soloBedsLost: Int,

            // Doubles
            @SerializedName("eight_two_wins_bedwars") val doublesWins: Int,
            @SerializedName("eight_two_losses_bedwars") val doublesLosses: Int,
            @SerializedName("eight_two_final_kills_bedwars") val doublesFinalKills: Int,
            @SerializedName("eight_two_final_deaths_bedwars") val doublesFinalDeaths: Int,
            @SerializedName("eight_two_kills_bedwars") val doublesKills: Int,
            @SerializedName("eight_two_deaths_bedwars") val doublesDeaths: Int,
            @SerializedName("eight_two_beds_broken_bedwars") val doublesBedsBroken: Int,
            @SerializedName("eight_two_beds_lost_bedwars") val doublesBedsLost: Int,

            // Threes
            @SerializedName("four_three_wins_bedwars") val threesWins: Int,
            @SerializedName("four_three_losses_bedwars") val threesLosses: Int,
            @SerializedName("four_three_final_kills_bedwars") val threesFinalKills: Int,
            @SerializedName("four_three_final_deaths_bedwars") val threesFinalDeaths: Int,
            @SerializedName("four_three_kills_bedwars") val threesKills: Int,
            @SerializedName("four_three_deaths_bedwars") val threesDeaths: Int,
            @SerializedName("four_three_beds_broken_bedwars") val threesBedsBroken: Int,
            @SerializedName("four_three_beds_lost_bedwars") val threesBedsLost: Int,

            // Fours
            @SerializedName("four_four_wins_bedwars") val foursWins: Int,
            @SerializedName("four_four_losses_bedwars") val foursLosses: Int,
            @SerializedName("four_four_final_kills_bedwars") val foursFinalKills: Int,
            @SerializedName("four_four_final_deaths_bedwars") val foursFinalDeaths: Int,
            @SerializedName("four_four_kills_bedwars") val foursKills: Int,
            @SerializedName("four_four_deaths_bedwars") val foursDeaths: Int,
            @SerializedName("four_four_beds_broken_bedwars") val foursBedsBroken: Int,
            @SerializedName("four_four_beds_lost_bedwars") val foursBedsLost: Int,
        )
    }
}
