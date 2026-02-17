package com.example.hypixelstats

data class Statistics(
    val displayName: String,
    val level: Int,
    val tokens: Int,
    val karma: Int,
    val overall: ModeStatistics,
    val solo: ModeStatistics,
    val doubles: ModeStatistics,
    val threes: ModeStatistics,
    val fours: ModeStatistics,
) {
    data class ModeStatistics(
        val wins: Int,
        val losses: Int,
        val finalKills: Int,
        val finalDeaths: Int,
        val kills: Int,
        val deaths: Int,
        val bedsBroken: Int,
        val bedsLost: Int,
    ) {
        val kdr: Double get() = calculateRatio(kills, deaths)
        val fkdr: Double get() = calculateRatio(finalKills, finalDeaths)
        val wlr: Double get() = calculateRatio(wins, losses)
        val bblr: Double get() = calculateRatio(bedsBroken, bedsLost)
    }

    companion object {
        fun from(playerData: PlayerData): Statistics {
            val player = playerData.player
            val bedwars = player.stats.bedwars

            return Statistics(
                displayName = player.displayName,
                level = calculateLevel(bedwars.experience),
                tokens = bedwars.tokens,
                karma = player.karma,
                overall = ModeStatistics(
                    wins = bedwars.overallWins,
                    losses = bedwars.overallLosses,
                    finalKills = bedwars.overallFinalKills,
                    finalDeaths = bedwars.overallFinalDeaths,
                    kills = bedwars.overallKills,
                    deaths = bedwars.overallDeaths,
                    bedsBroken = bedwars.overallBedsBroken,
                    bedsLost = bedwars.overallBedsLost,
                ),
                solo = ModeStatistics(
                    wins = bedwars.soloWins,
                    losses = bedwars.soloLosses,
                    finalKills = bedwars.soloFinalKills,
                    finalDeaths = bedwars.soloFinalDeaths,
                    kills = bedwars.soloKills,
                    deaths = bedwars.soloDeaths,
                    bedsBroken = bedwars.soloBedsBroken,
                    bedsLost = bedwars.soloBedsLost,
                ),
                doubles = ModeStatistics(
                    wins = bedwars.doublesWins,
                    losses = bedwars.doublesLosses,
                    finalKills = bedwars.doublesFinalKills,
                    finalDeaths = bedwars.doublesFinalDeaths,
                    kills = bedwars.doublesKills,
                    deaths = bedwars.doublesDeaths,
                    bedsBroken = bedwars.doublesBedsBroken,
                    bedsLost = bedwars.doublesBedsLost,
                ),
                threes = ModeStatistics(
                    wins = bedwars.threesWins,
                    losses = bedwars.threesLosses,
                    finalKills = bedwars.threesFinalKills,
                    finalDeaths = bedwars.threesFinalDeaths,
                    kills = bedwars.threesKills,
                    deaths = bedwars.threesDeaths,
                    bedsBroken = bedwars.threesBedsBroken,
                    bedsLost = bedwars.threesBedsLost,
                ),
                fours = ModeStatistics(
                    wins = bedwars.foursWins,
                    losses = bedwars.foursLosses,
                    finalKills = bedwars.foursFinalKills,
                    finalDeaths = bedwars.foursFinalDeaths,
                    kills = bedwars.foursKills,
                    deaths = bedwars.foursDeaths,
                    bedsBroken = bedwars.foursBedsBroken,
                    bedsLost = bedwars.foursBedsLost,
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