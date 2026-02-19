package com.example.bedstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Statistics(
    val displayName: String,
    val level: Int,
    val tokens: Int,
    val iron: Int,
    val gold: Int,
    val diamonds: Int,
    val emeralds: Int,

    val overall: ModeStatistics,
    val solo: ModeStatistics,
    val doubles: ModeStatistics,
    val threes: ModeStatistics,
    val fours: ModeStatistics,
    val core: ModeStatistics,
    val fours2: ModeStatistics,
    val rush: ModeStatistics,
    val ultimate: ModeStatistics,
    val castle: ModeStatistics,
    val voidless: ModeStatistics,
    val armed: ModeStatistics,
    val lucky: ModeStatistics,
    val swap: ModeStatistics,
    val oneblock: ModeStatistics
) : Parcelable {
    @Parcelize
    data class ModeStatistics(
        val wins: Int,
        val losses: Int,
        val finalKills: Int,
        val finalDeaths: Int,
        val kills: Int,
        val deaths: Int,
        val bedsBroken: Int,
        val bedsLost: Int
    ) : Parcelable {
        val kdr get() = calculateRatio(kills, deaths)
        val fkdr get() = calculateRatio(finalKills, finalDeaths)
        val wlr get() = calculateRatio(wins, losses)
        val bblr get() = calculateRatio(bedsBroken, bedsLost)
    }

    companion object {
        fun from(playerData: PlayerData): Statistics? {
            val player = playerData.player ?: return null // No player data available
            val bedwars = player.stats.bedwars ?: return null // No Bedwars stats available

            return Statistics(
                displayName = player.displayName,
                level = calculateLevel(bedwars.experience),
                tokens = bedwars.tokens,
                iron = bedwars.iron,
                gold = bedwars.gold,
                diamonds = bedwars.diamonds,
                emeralds = bedwars.emeralds,
                overall = ModeStatistics(
                    wins = bedwars.wins,
                    losses = bedwars.losses,
                    finalKills = bedwars.finalKills,
                    finalDeaths = bedwars.finalDeaths,
                    kills = bedwars.kills,
                    deaths = bedwars.deaths,
                    bedsBroken = bedwars.bedsBroken,
                    bedsLost = bedwars.bedsLost
                ),
                solo = ModeStatistics(
                    wins = bedwars.winsEightOne,
                    losses = bedwars.lossesEightOne,
                    finalKills = bedwars.finalKillsEightOne,
                    finalDeaths = bedwars.finalDeathsEightOne,
                    kills = bedwars.killsEightOne,
                    deaths = bedwars.deathsEightOne,
                    bedsBroken = bedwars.bedsBrokenEightOne,
                    bedsLost = bedwars.bedsLostEightOne
                ),
                doubles = ModeStatistics(
                    wins = bedwars.winsEightTwo,
                    losses = bedwars.lossesEightTwo,
                    finalKills = bedwars.finalKillsEightTwo,
                    finalDeaths = bedwars.finalDeathsEightTwo,
                    kills = bedwars.killsEightTwo,
                    deaths = bedwars.deathsEightTwo,
                    bedsBroken = bedwars.bedsBrokenEightTwo,
                    bedsLost = bedwars.bedsLostEightTwo
                ),
                threes = ModeStatistics(
                    wins = bedwars.winsFourThree,
                    losses = bedwars.lossesFourThree,
                    finalKills = bedwars.finalKillsFourThree,
                    finalDeaths = bedwars.finalDeathsFourThree,
                    kills = bedwars.killsFourThree,
                    deaths = bedwars.deathsFourThree,
                    bedsBroken = bedwars.bedsBrokenFourThree,
                    bedsLost = bedwars.bedsLostFourThree
                ),
                fours = ModeStatistics(
                    wins = bedwars.winsFourFour,
                    losses = bedwars.lossesFourFour,
                    finalKills = bedwars.finalKillsFourFour,
                    finalDeaths = bedwars.finalDeathsFourFour,
                    kills = bedwars.killsFourFour,
                    deaths = bedwars.deathsFourFour,
                    bedsBroken = bedwars.bedsBrokenFourFour,
                    bedsLost = bedwars.bedsLostFourFour
                ),
                core = ModeStatistics(
                    wins = bedwars.winsEightOne + bedwars.winsEightTwo + bedwars.winsFourThree + bedwars.winsFourFour,
                    losses = bedwars.lossesEightOne + bedwars.lossesEightTwo + bedwars.lossesFourThree + bedwars.lossesFourFour,
                    finalKills = bedwars.finalKillsEightOne + bedwars.finalKillsEightTwo + bedwars.finalKillsFourThree + bedwars.finalKillsFourFour,
                    finalDeaths = bedwars.finalDeathsEightOne + bedwars.finalDeathsEightTwo + bedwars.finalDeathsFourThree + bedwars.finalDeathsFourFour,
                    kills = bedwars.killsEightOne + bedwars.killsEightTwo + bedwars.killsFourThree + bedwars.killsFourFour,
                    deaths = bedwars.deathsEightOne + bedwars.deathsEightTwo + bedwars.deathsFourThree + bedwars.deathsFourFour,
                    bedsBroken = bedwars.bedsBrokenEightOne + bedwars.bedsBrokenEightTwo + bedwars.bedsBrokenFourThree + bedwars.bedsBrokenFourFour,
                    bedsLost = bedwars.bedsLostEightOne + bedwars.bedsLostEightTwo + bedwars.bedsLostFourThree + bedwars.bedsLostFourFour
                ),
                fours2 = ModeStatistics(
                    wins = bedwars.winsTwoFour,
                    losses = bedwars.lossesTwoFour,
                    finalKills = bedwars.finalKillsTwoFour,
                    finalDeaths = bedwars.finalDeathsTwoFour,
                    kills = bedwars.killsTwoFour,
                    deaths = bedwars.deathsTwoFour,
                    bedsBroken = bedwars.bedsBrokenTwoFour,
                    bedsLost = bedwars.bedsLostTwoFour
                ),
                rush = ModeStatistics(
                    wins = bedwars.winsEightOneRush + bedwars.winsEightTwoRush + bedwars.winsFourFourRush,
                    losses = bedwars.lossesEightOneRush + bedwars.lossesEightTwoRush + bedwars.lossesFourFourRush,
                    finalKills = bedwars.finalKillsEightOneRush + bedwars.finalKillsEightTwoRush + bedwars.finalKillsFourFourRush,
                    finalDeaths = bedwars.finalDeathsEightOneRush + bedwars.finalDeathsEightTwoRush + bedwars.finalDeathsFourFourRush,
                    kills = bedwars.killsEightOneRush + bedwars.killsEightTwoRush + bedwars.killsFourFourRush,
                    deaths = bedwars.deathsEightOneRush + bedwars.deathsEightTwoRush + bedwars.deathsFourFourRush,
                    bedsBroken = bedwars.bedsBrokenEightOneRush + bedwars.bedsBrokenEightTwoRush + bedwars.bedsBrokenFourFourRush,
                    bedsLost = bedwars.bedsLostEightOneRush + bedwars.bedsLostEightTwoRush + bedwars.bedsLostFourFourRush
                ),
                ultimate = ModeStatistics(
                    wins = bedwars.winsEightOneUltimate + bedwars.winsEightTwoUltimate + bedwars.winsFourFourUltimate,
                    losses = bedwars.lossesEightOneUltimate + bedwars.lossesEightTwoUltimate + bedwars.lossesFourFourUltimate,
                    finalKills = bedwars.finalKillsEightOneUltimate + bedwars.finalKillsEightTwoUltimate + bedwars.finalKillsFourFourUltimate,
                    finalDeaths = bedwars.finalDeathsEightOneUltimate + bedwars.finalDeathsEightTwoUltimate + bedwars.finalDeathsFourFourUltimate,
                    kills = bedwars.killsEightOneUltimate + bedwars.killsEightTwoUltimate + bedwars.killsFourFourUltimate,
                    deaths = bedwars.deathsEightOneUltimate + bedwars.deathsEightTwoUltimate + bedwars.deathsFourFourUltimate,
                    bedsBroken = bedwars.bedsBrokenEightOneUltimate + bedwars.bedsBrokenEightTwoUltimate + bedwars.bedsBrokenFourFourUltimate,
                    bedsLost = bedwars.bedsLostEightOneUltimate + bedwars.bedsLostEightTwoUltimate + bedwars.bedsLostFourFourUltimate
                ),
                castle = ModeStatistics(
                    wins = bedwars.winsCastle,
                    losses = bedwars.lossesCastle,
                    finalKills = bedwars.finalKillsCastle,
                    finalDeaths = bedwars.finalDeathsCastle,
                    kills = bedwars.killsCastle,
                    deaths = bedwars.deathsCastle,
                    bedsBroken = bedwars.bedsBrokenCastle,
                    bedsLost = bedwars.bedsLostCastle
                ),
                voidless = ModeStatistics(
                    wins = bedwars.winsEightTwoVoidless + bedwars.winsFourFourVoidless,
                    losses = bedwars.lossesEightTwoVoidless + bedwars.lossesFourFourVoidless,
                    finalKills = bedwars.finalKillsEightTwoVoidless + bedwars.finalKillsFourFourVoidless,
                    finalDeaths = bedwars.finalDeathsEightTwoVoidless + bedwars.finalDeathsFourFourVoidless,
                    kills = bedwars.killsEightTwoVoidless + bedwars.killsFourFourVoidless,
                    deaths = bedwars.deathsEightTwoVoidless + bedwars.deathsFourFourVoidless,
                    bedsBroken = bedwars.bedsBrokenEightTwoVoidless + bedwars.bedsBrokenFourFourVoidless,
                    bedsLost = bedwars.bedsLostEightTwoVoidless + bedwars.bedsLostFourFourVoidless
                ),
                armed = ModeStatistics(
                    wins = bedwars.winsEightTwoArmed + bedwars.winsFourFourArmed,
                    losses = bedwars.lossesEightTwoArmed + bedwars.lossesFourFourArmed,
                    finalKills = bedwars.finalKillsEightTwoArmed + bedwars.finalKillsFourFourArmed,
                    finalDeaths = bedwars.finalDeathsEightTwoArmed + bedwars.finalDeathsFourFourArmed,
                    kills = bedwars.killsEightTwoArmed + bedwars.killsFourFourArmed,
                    deaths = bedwars.deathsEightTwoArmed + bedwars.deathsFourFourArmed,
                    bedsBroken = bedwars.bedsBrokenEightTwoArmed + bedwars.bedsBrokenFourFourArmed,
                    bedsLost = bedwars.bedsLostEightTwoArmed + bedwars.bedsLostFourFourArmed
                ),
                lucky = ModeStatistics(
                    wins = bedwars.winsEightTwoLucky + bedwars.winsFourFourLucky,
                    losses = bedwars.lossesEightTwoLucky + bedwars.lossesFourFourLucky,
                    finalKills = bedwars.finalKillsEightTwoLucky + bedwars.finalKillsFourFourLucky,
                    finalDeaths = bedwars.finalDeathsEightTwoLucky + bedwars.finalDeathsFourFourLucky,
                    kills = bedwars.killsEightTwoLucky + bedwars.killsFourFourLucky,
                    deaths = bedwars.deathsEightTwoLucky + bedwars.deathsFourFourLucky,
                    bedsBroken = bedwars.bedsBrokenEightTwoLucky + bedwars.bedsBrokenFourFourLucky,
                    bedsLost = bedwars.bedsLostEightTwoLucky + bedwars.bedsLostFourFourLucky
                ),
                swap = ModeStatistics(
                    wins = bedwars.winsEightTwoSwap + bedwars.winsFourFourSwap,
                    losses = bedwars.lossesEightTwoSwap + bedwars.lossesFourFourSwap,
                    finalKills = bedwars.finalKillsEightTwoSwap + bedwars.finalKillsFourFourSwap,
                    finalDeaths = bedwars.finalDeathsEightTwoSwap + bedwars.finalDeathsFourFourSwap,
                    kills = bedwars.killsEightTwoSwap + bedwars.killsFourFourSwap,
                    deaths = bedwars.deathsEightTwoSwap + bedwars.deathsFourFourSwap,
                    bedsBroken = bedwars.bedsBrokenEightTwoSwap + bedwars.bedsBrokenFourFourSwap,
                    bedsLost = bedwars.bedsLostEightTwoSwap + bedwars.bedsLostFourFourSwap
                ),
                oneblock = ModeStatistics(
                    wins = bedwars.winsOneblock,
                    losses = bedwars.lossesOneblock,
                    finalKills = bedwars.finalKillsOneblock,
                    finalDeaths = bedwars.finalDeathsOneblock,
                    kills = bedwars.killsOneblock,
                    deaths = bedwars.deathsOneblock,
                    bedsBroken = bedwars.bedsBrokenOneblock,
                    bedsLost = bedwars.bedsLostOneblock
                ),
            )
        }

        private fun calculateRatio(a: Int, b: Int): Double {
            if (b == 0) return 0.0
            return a.toDouble() / b
        }

        private fun calculateLevel(exp: Int): Int {
            if (exp <= 0) return 0

            var stars = (exp / 487000) * 100
            var rem = exp % 487000
            for (cost in intArrayOf(500, 1000, 2000, 3500)) {
                if (rem >= cost) {
                    rem -= cost
                    stars++
                } else {
                    return stars
                }
            }
            stars += rem / 5000
            return stars
        }
    }
}