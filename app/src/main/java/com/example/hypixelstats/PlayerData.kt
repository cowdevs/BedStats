package com.example.hypixelstats

import com.google.gson.annotations.SerializedName

data class PlayerData(
    val player: HypixelPlayer,
) {
    data class HypixelPlayer(
        @SerializedName("displayname") val displayName: String,
        val stats: HypixelStatistics,

        // Rank information (not always present)
        val packageRank: String? = null,
        val newPackageRank: String? = null,
        val monthlyPackageRank: String? = null,
        val rankPlusColor: String? = null,
        val monthlyRankColor: String? = null,
        val rank: String? = null,
        val prefix: String? = null
    )

    data class HypixelStatistics(
        @SerializedName("Bedwars") val bedwars: BedwarsStatistics
    ) {
        data class BedwarsStatistics(
            @SerializedName("Experience") val experience: Int,
            @SerializedName("coins") val tokens: Int,

            // Resources
            @SerializedName("emerald_resources_collected_bedwars") val emeralds: Int,
            @SerializedName("diamond_resources_collected_bedwars") val diamonds: Int,
            @SerializedName("gold_resources_collected_bedwars") val gold: Int,
            @SerializedName("iron_resources_collected_bedwars") val iron: Int,

            // Overall
            @SerializedName("wins_bedwars") val wins: Int,
            @SerializedName("losses_bedwars") val losses: Int,
            @SerializedName("final_kills_bedwars") val finalKills: Int,
            @SerializedName("final_deaths_bedwars") val finalDeaths: Int,
            @SerializedName("kills_bedwars") val kills: Int,
            @SerializedName("deaths_bedwars") val deaths: Int,
            @SerializedName("beds_broken_bedwars") val bedsBroken: Int,
            @SerializedName("beds_lost_bedwars") val bedsLost: Int,

            // Solo
            @SerializedName("eight_one_wins_bedwars") val winsEightOne: Int,
            @SerializedName("eight_one_losses_bedwars") val lossesEightOne: Int,
            @SerializedName("eight_one_final_kills_bedwars") val finalKillsEightOne: Int,
            @SerializedName("eight_one_final_deaths_bedwars") val finalDeathsEightOne: Int,
            @SerializedName("eight_one_kills_bedwars") val killsEightOne: Int,
            @SerializedName("eight_one_deaths_bedwars") val deathsEightOne: Int,
            @SerializedName("eight_one_beds_broken_bedwars") val bedsBrokenEightOne: Int,
            @SerializedName("eight_one_beds_lost_bedwars") val bedsLostEightOne: Int,

            // Doubles
            @SerializedName("eight_two_wins_bedwars") val winsEightTwo: Int,
            @SerializedName("eight_two_losses_bedwars") val lossesEightTwo: Int,
            @SerializedName("eight_two_final_kills_bedwars") val finalKillsEightTwo: Int,
            @SerializedName("eight_two_final_deaths_bedwars") val finalDeathsEightTwo: Int,
            @SerializedName("eight_two_kills_bedwars") val killsEightTwo: Int,
            @SerializedName("eight_two_deaths_bedwars") val deathsEightTwo: Int,
            @SerializedName("eight_two_beds_broken_bedwars") val bedsBrokenEightTwo: Int,
            @SerializedName("eight_two_beds_lost_bedwars") val bedsLostEightTwo: Int,

            // Threes
            @SerializedName("four_three_wins_bedwars") val winsFourThree: Int,
            @SerializedName("four_three_losses_bedwars") val lossesFourThree: Int,
            @SerializedName("four_three_final_kills_bedwars") val finalKillsFourThree: Int,
            @SerializedName("four_three_final_deaths_bedwars") val finalDeathsFourThree: Int,
            @SerializedName("four_three_kills_bedwars") val killsFourThree: Int,
            @SerializedName("four_three_deaths_bedwars") val deathsFourThree: Int,
            @SerializedName("four_three_beds_broken_bedwars") val bedsBrokenFourThree: Int,
            @SerializedName("four_three_beds_lost_bedwars") val bedsLostFourThree: Int,

            // Fours
            @SerializedName("four_four_wins_bedwars") val winsFourFour: Int,
            @SerializedName("four_four_losses_bedwars") val lossesFourFour: Int,
            @SerializedName("four_four_final_kills_bedwars") val finalKillsFourFour: Int,
            @SerializedName("four_four_final_deaths_bedwars") val finalDeathsFourFour: Int,
            @SerializedName("four_four_kills_bedwars") val killsFourFour: Int,
            @SerializedName("four_four_deaths_bedwars") val deathsFourFour: Int,
            @SerializedName("four_four_beds_broken_bedwars") val bedsBrokenFourFour: Int,
            @SerializedName("four_four_beds_lost_bedwars") val bedsLostFourFour: Int,

            // 4v4
            @SerializedName("two_four_wins_bedwars") val winsTwoFour: Int,
            @SerializedName("two_four_losses_bedwars") val lossesTwoFour: Int,
            @SerializedName("two_four_final_kills_bedwars") val finalKillsTwoFour: Int,
            @SerializedName("two_four_final_deaths_bedwars") val finalDeathsTwoFour: Int,
            @SerializedName("two_four_kills_bedwars") val killsTwoFour: Int,
            @SerializedName("two_four_deaths_bedwars") val deathsTwoFour: Int,
            @SerializedName("two_four_beds_broken_bedwars") val bedsBrokenTwoFour: Int,
            @SerializedName("two_four_beds_lost_bedwars") val bedsLostTwoFour: Int,

            // Castle
            @SerializedName("castle_wins_bedwars") val winsCastle: Int,
            @SerializedName("castle_losses_bedwars") val lossesCastle: Int,
            @SerializedName("castle_final_kills_bedwars") val finalKillsCastle: Int,
            @SerializedName("castle_final_deaths_bedwars") val finalDeathsCastle: Int,
            @SerializedName("castle_kills_bedwars") val killsCastle: Int,
            @SerializedName("castle_deaths_bedwars") val deathsCastle: Int,
            @SerializedName("castle_beds_broken_bedwars") val bedsBrokenCastle: Int,
            @SerializedName("castle_beds_lost_bedwars") val bedsLostCastle: Int,

            // Rush
            @SerializedName("eight_one_rush_wins_bedwars") val winsEightOneRush: Int,
            @SerializedName("eight_one_rush_losses_bedwars") val lossesEightOneRush: Int,
            @SerializedName("eight_one_rush_final_kills_bedwars") val finalKillsEightOneRush: Int,
            @SerializedName("eight_one_rush_final_deaths_bedwars") val finalDeathsEightOneRush: Int,
            @SerializedName("eight_one_rush_kills_bedwars") val killsEightOneRush: Int,
            @SerializedName("eight_one_rush_deaths_bedwars") val deathsEightOneRush: Int,
            @SerializedName("eight_one_rush_beds_broken_bedwars") val bedsBrokenEightOneRush: Int,
            @SerializedName("eight_one_rush_beds_lost_bedwars") val bedsLostEightOneRush: Int,

            @SerializedName("eight_two_rush_wins_bedwars") val winsEightTwoRush: Int,
            @SerializedName("eight_two_rush_losses_bedwars") val lossesEightTwoRush: Int,
            @SerializedName("eight_two_rush_final_kills_bedwars") val finalKillsEightTwoRush: Int,
            @SerializedName("eight_two_rush_final_deaths_bedwars") val finalDeathsEightTwoRush: Int,
            @SerializedName("eight_two_rush_kills_bedwars") val killsEightTwoRush: Int,
            @SerializedName("eight_two_rush_deaths_bedwars") val deathsEightTwoRush: Int,
            @SerializedName("eight_two_rush_beds_broken_bedwars") val bedsBrokenEightTwoRush: Int,
            @SerializedName("eight_two_rush_beds_lost_bedwars") val bedsLostEightTwoRush: Int,

            @SerializedName("four_four_rush_wins_bedwars") val winsFourFourRush: Int,
            @SerializedName("four_four_rush_losses_bedwars") val lossesFourFourRush: Int,
            @SerializedName("four_four_rush_final_kills_bedwars") val finalKillsFourFourRush: Int,
            @SerializedName("four_four_rush_final_deaths_bedwars") val finalDeathsFourFourRush: Int,
            @SerializedName("four_four_rush_kills_bedwars") val killsFourFourRush: Int,
            @SerializedName("four_four_rush_deaths_bedwars") val deathsFourFourRush: Int,
            @SerializedName("four_four_rush_beds_broken_bedwars") val bedsBrokenFourFourRush: Int,
            @SerializedName("four_four_rush_beds_lost_bedwars") val bedsLostFourFourRush: Int,

            // Ultimate
            @SerializedName("eight_one_ultimate_wins_bedwars") val winsEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_losses_bedwars") val lossesEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_final_kills_bedwars") val finalKillsEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_final_deaths_bedwars") val finalDeathsEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_kills_bedwars") val killsEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_deaths_bedwars") val deathsEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_beds_broken_bedwars") val bedsBrokenEightOneUltimate: Int,
            @SerializedName("eight_one_ultimate_beds_lost_bedwars") val bedsLostEightOneUltimate: Int,

            @SerializedName("eight_two_ultimate_wins_bedwars") val winsEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_losses_bedwars") val lossesEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_final_kills_bedwars") val finalKillsEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_final_deaths_bedwars") val finalDeathsEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_kills_bedwars") val killsEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_deaths_bedwars") val deathsEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_beds_broken_bedwars") val bedsBrokenEightTwoUltimate: Int,
            @SerializedName("eight_two_ultimate_beds_lost_bedwars") val bedsLostEightTwoUltimate: Int,

            @SerializedName("four_four_ultimate_wins_bedwars") val winsFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_losses_bedwars") val lossesFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_final_kills_bedwars") val finalKillsFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_final_deaths_bedwars") val finalDeathsFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_kills_bedwars") val killsFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_deaths_bedwars") val deathsFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_beds_broken_bedwars") val bedsBrokenFourFourUltimate: Int,
            @SerializedName("four_four_ultimate_beds_lost_bedwars") val bedsLostFourFourUltimate: Int,

            // Voidless
            @SerializedName("eight_two_voidless_wins_bedwars") val winsEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_losses_bedwars") val lossesEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_final_kills_bedwars") val finalKillsEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_final_deaths_bedwars") val finalDeathsEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_kills_bedwars") val killsEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_deaths_bedwars") val deathsEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_beds_broken_bedwars") val bedsBrokenEightTwoVoidless: Int,
            @SerializedName("eight_two_voidless_beds_lost_bedwars") val bedsLostEightTwoVoidless: Int,

            @SerializedName("four_four_voidless_wins_bedwars") val winsFourFourVoidless: Int,
            @SerializedName("four_four_voidless_losses_bedwars") val lossesFourFourVoidless: Int,
            @SerializedName("four_four_voidless_final_kills_bedwars") val finalKillsFourFourVoidless: Int,
            @SerializedName("four_four_voidless_final_deaths_bedwars") val finalDeathsFourFourVoidless: Int,
            @SerializedName("four_four_voidless_kills_bedwars") val killsFourFourVoidless: Int,
            @SerializedName("four_four_voidless_deaths_bedwars") val deathsFourFourVoidless: Int,
            @SerializedName("four_four_voidless_beds_broken_bedwars") val bedsBrokenFourFourVoidless: Int,
            @SerializedName("four_four_voidless_beds_lost_bedwars") val bedsLostFourFourVoidless: Int,

            // Armed
            @SerializedName("eight_two_armed_wins_bedwars") val winsEightTwoArmed: Int,
            @SerializedName("eight_two_armed_losses_bedwars") val lossesEightTwoArmed: Int,
            @SerializedName("eight_two_armed_final_kills_bedwars") val finalKillsEightTwoArmed: Int,
            @SerializedName("eight_two_armed_final_deaths_bedwars") val finalDeathsEightTwoArmed: Int,
            @SerializedName("eight_two_armed_kills_bedwars") val killsEightTwoArmed: Int,
            @SerializedName("eight_two_armed_deaths_bedwars") val deathsEightTwoArmed: Int,
            @SerializedName("eight_two_armed_beds_broken_bedwars") val bedsBrokenEightTwoArmed: Int,
            @SerializedName("eight_two_armed_beds_lost_bedwars") val bedsLostEightTwoArmed: Int,

            @SerializedName("four_four_armed_wins_bedwars") val winsFourFourArmed: Int,
            @SerializedName("four_four_armed_losses_bedwars") val lossesFourFourArmed: Int,
            @SerializedName("four_four_armed_final_kills_bedwars") val finalKillsFourFourArmed: Int,
            @SerializedName("four_four_armed_final_deaths_bedwars") val finalDeathsFourFourArmed: Int,
            @SerializedName("four_four_armed_kills_bedwars") val killsFourFourArmed: Int,
            @SerializedName("four_four_armed_deaths_bedwars") val deathsFourFourArmed: Int,
            @SerializedName("four_four_armed_beds_broken_bedwars") val bedsBrokenFourFourArmed: Int,
            @SerializedName("four_four_armed_beds_lost_bedwars") val bedsLostFourFourArmed: Int,
            
            // Lucky
            @SerializedName("eight_two_lucky_wins_bedwars") val winsEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_losses_bedwars") val lossesEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_final_kills_bedwars") val finalKillsEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_final_deaths_bedwars") val finalDeathsEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_kills_bedwars") val killsEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_deaths_bedwars") val deathsEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_beds_broken_bedwars") val bedsBrokenEightTwoLucky: Int,
            @SerializedName("eight_two_lucky_beds_lost_bedwars") val bedsLostEightTwoLucky: Int,

            @SerializedName("four_four_lucky_wins_bedwars") val winsFourFourLucky: Int,
            @SerializedName("four_four_lucky_losses_bedwars") val lossesFourFourLucky: Int,
            @SerializedName("four_four_lucky_final_kills_bedwars") val finalKillsFourFourLucky: Int,
            @SerializedName("four_four_lucky_final_deaths_bedwars") val finalDeathsFourFourLucky: Int,
            @SerializedName("four_four_lucky_kills_bedwars") val killsFourFourLucky: Int,
            @SerializedName("four_four_lucky_deaths_bedwars") val deathsFourFourLucky: Int,
            @SerializedName("four_four_lucky_beds_broken_bedwars") val bedsBrokenFourFourLucky: Int,
            @SerializedName("four_four_lucky_beds_lost_bedwars") val bedsLostFourFourLucky: Int,

            // Swap
            @SerializedName("eight_two_swap_wins_bedwars") val winsEightTwoSwap: Int,
            @SerializedName("eight_two_swap_losses_bedwars") val lossesEightTwoSwap: Int,
            @SerializedName("eight_two_swap_final_kills_bedwars") val finalKillsEightTwoSwap: Int,
            @SerializedName("eight_two_swap_final_deaths_bedwars") val finalDeathsEightTwoSwap: Int,
            @SerializedName("eight_two_swap_kills_bedwars") val killsEightTwoSwap: Int,
            @SerializedName("eight_two_swap_deaths_bedwars") val deathsEightTwoSwap: Int,
            @SerializedName("eight_two_swap_beds_broken_bedwars") val bedsBrokenEightTwoSwap: Int,
            @SerializedName("eight_two_swap_beds_lost_bedwars") val bedsLostEightTwoSwap: Int,

            @SerializedName("four_four_swap_wins_bedwars") val winsFourFourSwap: Int,
            @SerializedName("four_four_swap_losses_bedwars") val lossesFourFourSwap: Int,
            @SerializedName("four_four_swap_final_kills_bedwars") val finalKillsFourFourSwap: Int,
            @SerializedName("four_four_swap_final_deaths_bedwars") val finalDeathsFourFourSwap: Int,
            @SerializedName("four_four_swap_kills_bedwars") val killsFourFourSwap: Int,
            @SerializedName("four_four_swap_deaths_bedwars") val deathsFourFourSwap: Int,
            @SerializedName("four_four_swap_beds_broken_bedwars") val bedsBrokenFourFourSwap: Int,
            @SerializedName("four_four_swap_beds_lost_bedwars") val bedsLostFourFourSwap: Int,

            // One Block
            @SerializedName("eight_one_oneblock_wins_bedwars") val winsOneblock: Int,
            @SerializedName("eight_one_oneblock_losses_bedwars") val lossesOneblock: Int,
            @SerializedName("eight_one_oneblock_final_kills_bedwars") val finalKillsOneblock: Int,
            @SerializedName("eight_one_oneblock_final_deaths_bedwars") val finalDeathsOneblock: Int,
            @SerializedName("eight_one_oneblock_kills_bedwars") val killsOneblock: Int,
            @SerializedName("eight_one_oneblock_deaths_bedwars") val deathsOneblock: Int,
            @SerializedName("eight_one_oneblock_beds_broken_bedwars") val bedsBrokenOneblock: Int,
            @SerializedName("eight_one_oneblock_beds_lost_bedwars") val bedsLostOneblock: Int,
        )
    }
}
