package com.example.bedstats

import com.google.gson.annotations.SerializedName

data class Player(
	val name: String,
	val id: String
)

data class PlayerData(
	val player: HypixelPlayer?,
) {
	data class HypixelPlayer(
		@SerializedName("displayname") val displayName: String,
		val stats: HypixelStatistics?,

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
		@SerializedName("Bedwars") val bedwars: BedwarsStatistics?
	) {
		data class BedwarsStatistics(
			@SerializedName("Experience") val experience: Int = 0,
			@SerializedName("coins") val tokens: Int = 0,
			val winstreak: Int = 0,

			// Resources
			@SerializedName("emerald_resources_collected_bedwars") val emeralds: Int = 0,
			@SerializedName("diamond_resources_collected_bedwars") val diamonds: Int = 0,
			@SerializedName("gold_resources_collected_bedwars") val gold: Int = 0,
			@SerializedName("iron_resources_collected_bedwars") val iron: Int = 0,

			// Overall
			@SerializedName("wins_bedwars") val wins: Int = 0,
			@SerializedName("losses_bedwars") val losses: Int = 0,
			@SerializedName("final_kills_bedwars") val finalKills: Int = 0,
			@SerializedName("final_deaths_bedwars") val finalDeaths: Int = 0,
			@SerializedName("kills_bedwars") val kills: Int = 0,
			@SerializedName("deaths_bedwars") val deaths: Int = 0,
			@SerializedName("beds_broken_bedwars") val bedsBroken: Int = 0,
			@SerializedName("beds_lost_bedwars") val bedsLost: Int = 0,

			// Solo
			@SerializedName("eight_one_wins_bedwars") val winsEightOne: Int = 0,
			@SerializedName("eight_one_losses_bedwars") val lossesEightOne: Int = 0,
			@SerializedName("eight_one_final_kills_bedwars") val finalKillsEightOne: Int = 0,
			@SerializedName("eight_one_final_deaths_bedwars") val finalDeathsEightOne: Int = 0,
			@SerializedName("eight_one_kills_bedwars") val killsEightOne: Int = 0,
			@SerializedName("eight_one_deaths_bedwars") val deathsEightOne: Int = 0,
			@SerializedName("eight_one_beds_broken_bedwars") val bedsBrokenEightOne: Int = 0,
			@SerializedName("eight_one_beds_lost_bedwars") val bedsLostEightOne: Int = 0,

			// Doubles
			@SerializedName("eight_two_wins_bedwars") val winsEightTwo: Int = 0,
			@SerializedName("eight_two_losses_bedwars") val lossesEightTwo: Int = 0,
			@SerializedName("eight_two_final_kills_bedwars") val finalKillsEightTwo: Int = 0,
			@SerializedName("eight_two_final_deaths_bedwars") val finalDeathsEightTwo: Int = 0,
			@SerializedName("eight_two_kills_bedwars") val killsEightTwo: Int = 0,
			@SerializedName("eight_two_deaths_bedwars") val deathsEightTwo: Int = 0,
			@SerializedName("eight_two_beds_broken_bedwars") val bedsBrokenEightTwo: Int = 0,
			@SerializedName("eight_two_beds_lost_bedwars") val bedsLostEightTwo: Int = 0,

			// Threes
			@SerializedName("four_three_wins_bedwars") val winsFourThree: Int = 0,
			@SerializedName("four_three_losses_bedwars") val lossesFourThree: Int = 0,
			@SerializedName("four_three_final_kills_bedwars") val finalKillsFourThree: Int = 0,
			@SerializedName("four_three_final_deaths_bedwars") val finalDeathsFourThree: Int = 0,
			@SerializedName("four_three_kills_bedwars") val killsFourThree: Int = 0,
			@SerializedName("four_three_deaths_bedwars") val deathsFourThree: Int = 0,
			@SerializedName("four_three_beds_broken_bedwars") val bedsBrokenFourThree: Int = 0,
			@SerializedName("four_three_beds_lost_bedwars") val bedsLostFourThree: Int = 0,

			// Fours
			@SerializedName("four_four_wins_bedwars") val winsFourFour: Int = 0,
			@SerializedName("four_four_losses_bedwars") val lossesFourFour: Int = 0,
			@SerializedName("four_four_final_kills_bedwars") val finalKillsFourFour: Int = 0,
			@SerializedName("four_four_final_deaths_bedwars") val finalDeathsFourFour: Int = 0,
			@SerializedName("four_four_kills_bedwars") val killsFourFour: Int = 0,
			@SerializedName("four_four_deaths_bedwars") val deathsFourFour: Int = 0,
			@SerializedName("four_four_beds_broken_bedwars") val bedsBrokenFourFour: Int = 0,
			@SerializedName("four_four_beds_lost_bedwars") val bedsLostFourFour: Int = 0,

			// 4v4
			@SerializedName("two_four_wins_bedwars") val winsTwoFour: Int = 0,
			@SerializedName("two_four_losses_bedwars") val lossesTwoFour: Int = 0,
			@SerializedName("two_four_final_kills_bedwars") val finalKillsTwoFour: Int = 0,
			@SerializedName("two_four_final_deaths_bedwars") val finalDeathsTwoFour: Int = 0,
			@SerializedName("two_four_kills_bedwars") val killsTwoFour: Int = 0,
			@SerializedName("two_four_deaths_bedwars") val deathsTwoFour: Int = 0,
			@SerializedName("two_four_beds_broken_bedwars") val bedsBrokenTwoFour: Int = 0,
			@SerializedName("two_four_beds_lost_bedwars") val bedsLostTwoFour: Int = 0,

			// Armed
			@SerializedName("eight_two_armed_wins_bedwars") val winsEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_losses_bedwars") val lossesEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_final_kills_bedwars") val finalKillsEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_final_deaths_bedwars") val finalDeathsEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_kills_bedwars") val killsEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_deaths_bedwars") val deathsEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_beds_broken_bedwars") val bedsBrokenEightTwoArmed: Int = 0,
			@SerializedName("eight_two_armed_beds_lost_bedwars") val bedsLostEightTwoArmed: Int = 0,

			@SerializedName("four_four_armed_wins_bedwars") val winsFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_losses_bedwars") val lossesFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_final_kills_bedwars") val finalKillsFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_final_deaths_bedwars") val finalDeathsFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_kills_bedwars") val killsFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_deaths_bedwars") val deathsFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_beds_broken_bedwars") val bedsBrokenFourFourArmed: Int = 0,
			@SerializedName("four_four_armed_beds_lost_bedwars") val bedsLostFourFourArmed: Int = 0,

			// Castle
			@SerializedName("castle_wins_bedwars") val winsCastle: Int = 0,
			@SerializedName("castle_losses_bedwars") val lossesCastle: Int = 0,
			@SerializedName("castle_final_kills_bedwars") val finalKillsCastle: Int = 0,
			@SerializedName("castle_final_deaths_bedwars") val finalDeathsCastle: Int = 0,
			@SerializedName("castle_kills_bedwars") val killsCastle: Int = 0,
			@SerializedName("castle_deaths_bedwars") val deathsCastle: Int = 0,
			@SerializedName("castle_beds_broken_bedwars") val bedsBrokenCastle: Int = 0,
			@SerializedName("castle_beds_lost_bedwars") val bedsLostCastle: Int = 0,

			// Lucky
			@SerializedName("eight_two_lucky_wins_bedwars") val winsEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_losses_bedwars") val lossesEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_final_kills_bedwars") val finalKillsEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_final_deaths_bedwars") val finalDeathsEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_kills_bedwars") val killsEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_deaths_bedwars") val deathsEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_beds_broken_bedwars") val bedsBrokenEightTwoLucky: Int = 0,
			@SerializedName("eight_two_lucky_beds_lost_bedwars") val bedsLostEightTwoLucky: Int = 0,

			@SerializedName("four_four_lucky_wins_bedwars") val winsFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_losses_bedwars") val lossesFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_final_kills_bedwars") val finalKillsFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_final_deaths_bedwars") val finalDeathsFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_kills_bedwars") val killsFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_deaths_bedwars") val deathsFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_beds_broken_bedwars") val bedsBrokenFourFourLucky: Int = 0,
			@SerializedName("four_four_lucky_beds_lost_bedwars") val bedsLostFourFourLucky: Int = 0,

			// Rush
			@SerializedName("eight_one_rush_wins_bedwars") val winsEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_losses_bedwars") val lossesEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_final_kills_bedwars") val finalKillsEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_final_deaths_bedwars") val finalDeathsEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_kills_bedwars") val killsEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_deaths_bedwars") val deathsEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_beds_broken_bedwars") val bedsBrokenEightOneRush: Int = 0,
			@SerializedName("eight_one_rush_beds_lost_bedwars") val bedsLostEightOneRush: Int = 0,

			@SerializedName("eight_two_rush_wins_bedwars") val winsEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_losses_bedwars") val lossesEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_final_kills_bedwars") val finalKillsEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_final_deaths_bedwars") val finalDeathsEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_kills_bedwars") val killsEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_deaths_bedwars") val deathsEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_beds_broken_bedwars") val bedsBrokenEightTwoRush: Int = 0,
			@SerializedName("eight_two_rush_beds_lost_bedwars") val bedsLostEightTwoRush: Int = 0,

			@SerializedName("four_four_rush_wins_bedwars") val winsFourFourRush: Int = 0,
			@SerializedName("four_four_rush_losses_bedwars") val lossesFourFourRush: Int = 0,
			@SerializedName("four_four_rush_final_kills_bedwars") val finalKillsFourFourRush: Int = 0,
			@SerializedName("four_four_rush_final_deaths_bedwars") val finalDeathsFourFourRush: Int = 0,
			@SerializedName("four_four_rush_kills_bedwars") val killsFourFourRush: Int = 0,
			@SerializedName("four_four_rush_deaths_bedwars") val deathsFourFourRush: Int = 0,
			@SerializedName("four_four_rush_beds_broken_bedwars") val bedsBrokenFourFourRush: Int = 0,
			@SerializedName("four_four_rush_beds_lost_bedwars") val bedsLostFourFourRush: Int = 0,

			// Swap
			@SerializedName("eight_two_swap_wins_bedwars") val winsEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_losses_bedwars") val lossesEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_final_kills_bedwars") val finalKillsEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_final_deaths_bedwars") val finalDeathsEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_kills_bedwars") val killsEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_deaths_bedwars") val deathsEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_beds_broken_bedwars") val bedsBrokenEightTwoSwap: Int = 0,
			@SerializedName("eight_two_swap_beds_lost_bedwars") val bedsLostEightTwoSwap: Int = 0,

			@SerializedName("four_four_swap_wins_bedwars") val winsFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_losses_bedwars") val lossesFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_final_kills_bedwars") val finalKillsFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_final_deaths_bedwars") val finalDeathsFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_kills_bedwars") val killsFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_deaths_bedwars") val deathsFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_beds_broken_bedwars") val bedsBrokenFourFourSwap: Int = 0,
			@SerializedName("four_four_swap_beds_lost_bedwars") val bedsLostFourFourSwap: Int = 0,

			// Ultimate
			@SerializedName("eight_one_ultimate_wins_bedwars") val winsEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_losses_bedwars") val lossesEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_final_kills_bedwars") val finalKillsEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_final_deaths_bedwars") val finalDeathsEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_kills_bedwars") val killsEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_deaths_bedwars") val deathsEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_beds_broken_bedwars") val bedsBrokenEightOneUltimate: Int = 0,
			@SerializedName("eight_one_ultimate_beds_lost_bedwars") val bedsLostEightOneUltimate: Int = 0,

			@SerializedName("eight_two_ultimate_wins_bedwars") val winsEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_losses_bedwars") val lossesEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_final_kills_bedwars") val finalKillsEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_final_deaths_bedwars") val finalDeathsEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_kills_bedwars") val killsEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_deaths_bedwars") val deathsEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_beds_broken_bedwars") val bedsBrokenEightTwoUltimate: Int = 0,
			@SerializedName("eight_two_ultimate_beds_lost_bedwars") val bedsLostEightTwoUltimate: Int = 0,

			@SerializedName("four_four_ultimate_wins_bedwars") val winsFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_losses_bedwars") val lossesFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_final_kills_bedwars") val finalKillsFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_final_deaths_bedwars") val finalDeathsFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_kills_bedwars") val killsFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_deaths_bedwars") val deathsFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_beds_broken_bedwars") val bedsBrokenFourFourUltimate: Int = 0,
			@SerializedName("four_four_ultimate_beds_lost_bedwars") val bedsLostFourFourUltimate: Int = 0,

			// Underworld
			@SerializedName("eight_two_underworld_wins_bedwars") val winsEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_losses_bedwars") val lossesEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_final_kills_bedwars") val finalKillsEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_final_deaths_bedwars") val finalDeathsEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_kills_bedwars") val killsEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_deaths_bedwars") val deathsEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_beds_broken_bedwars") val bedsBrokenEightTwoUnderworld: Int = 0,
			@SerializedName("eight_two_underworld_beds_lost_bedwars") val bedsLostEightTwoUnderworld: Int = 0,

			@SerializedName("four_four_underworld_wins_bedwars") val winsFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_losses_bedwars") val lossesFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_final_kills_bedwars") val finalKillsFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_final_deaths_bedwars") val finalDeathsFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_kills_bedwars") val killsFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_deaths_bedwars") val deathsFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_beds_broken_bedwars") val bedsBrokenFourFourUnderworld: Int = 0,
			@SerializedName("four_four_underworld_beds_lost_bedwars") val bedsLostFourFourUnderworld: Int = 0,

			// Voidless
			@SerializedName("eight_two_voidless_wins_bedwars") val winsEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_losses_bedwars") val lossesEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_final_kills_bedwars") val finalKillsEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_final_deaths_bedwars") val finalDeathsEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_kills_bedwars") val killsEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_deaths_bedwars") val deathsEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_beds_broken_bedwars") val bedsBrokenEightTwoVoidless: Int = 0,
			@SerializedName("eight_two_voidless_beds_lost_bedwars") val bedsLostEightTwoVoidless: Int = 0,

			@SerializedName("four_four_voidless_wins_bedwars") val winsFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_losses_bedwars") val lossesFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_final_kills_bedwars") val finalKillsFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_final_deaths_bedwars") val finalDeathsFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_kills_bedwars") val killsFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_deaths_bedwars") val deathsFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_beds_broken_bedwars") val bedsBrokenFourFourVoidless: Int = 0,
			@SerializedName("four_four_voidless_beds_lost_bedwars") val bedsLostFourFourVoidless: Int = 0,

			// One Block
			@SerializedName("eight_one_oneblock_wins_bedwars") val winsOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_losses_bedwars") val lossesOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_final_kills_bedwars") val finalKillsOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_final_deaths_bedwars") val finalDeathsOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_kills_bedwars") val killsOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_deaths_bedwars") val deathsOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_beds_broken_bedwars") val bedsBrokenOneblock: Int = 0,
			@SerializedName("eight_one_oneblock_beds_lost_bedwars") val bedsLostOneblock: Int = 0,
		)
	}
}
