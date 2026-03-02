package com.example.bedstats

object RankFormatHelper {
    data class TagComponent(val color: Int, val text: String)

    fun makeRank(
        rank: String,
        color: Int,
        plus: String?,
        plusColor: Int?,
        bracketColor: Int = color
    ): List<TagComponent> {
        return if (plus != null && plusColor != null) {
            listOf(
                TagComponent(bracketColor, "["),
                TagComponent(color, rank),
                TagComponent(plusColor, plus),
                TagComponent(bracketColor, "]")
            )
        } else {
            listOf(
                TagComponent(bracketColor, "["),
                TagComponent(color, rank),
                TagComponent(bracketColor, "]")
            )
        }
    }

    val colors: Map<String, Int> = mapOf(
        "BLACK" to R.color.minecraft_black,
        "DARK_BLUE" to R.color.minecraft_dark_blue,
        "DARK_GREEN" to R.color.minecraft_dark_green,
        "DARK_AQUA" to R.color.minecraft_dark_aqua,
        "DARK_RED" to R.color.minecraft_dark_red,
        "DARK_PURPLE" to R.color.minecraft_dark_purple,
        "GOLD" to R.color.minecraft_gold,
        "GRAY" to R.color.minecraft_gray,
        "DARK_GRAY" to R.color.minecraft_dark_gray,
        "BLUE" to R.color.minecraft_blue,
        "GREEN" to R.color.minecraft_green,
        "AQUA" to R.color.minecraft_aqua,
        "RED" to R.color.minecraft_red,
        "LIGHT_PURPLE" to R.color.minecraft_light_purple,
        "YELLOW" to R.color.minecraft_yellow,
        "WHITE" to R.color.minecraft_white,
        "0" to R.color.minecraft_black,
        "1" to R.color.minecraft_dark_blue,
        "2" to R.color.minecraft_dark_green,
        "3" to R.color.minecraft_dark_aqua,
        "4" to R.color.minecraft_dark_red,
        "5" to R.color.minecraft_dark_purple,
        "6" to R.color.minecraft_gold,
        "7" to R.color.minecraft_gray,
        "8" to R.color.minecraft_dark_gray,
        "9" to R.color.minecraft_blue,
        "a" to R.color.minecraft_green,
        "b" to R.color.minecraft_aqua,
        "c" to R.color.minecraft_red,
        "d" to R.color.minecraft_light_purple,
        "e" to R.color.minecraft_yellow,
        "f" to R.color.minecraft_white
    )

    fun calcTag(playerData: PlayerData): List<TagComponent> {
        val player = playerData.player!!

        var packageRank = player.packageRank
        var newPackageRank = player.newPackageRank
        var monthlyPackageRank = player.monthlyPackageRank
        val rankPlusColor = player.rankPlusColor
        val monthlyRankColor = player.monthlyRankColor
        var rank = player.rank
        val prefix = player.prefix

        if (rank == "NORMAL") rank = null
        if (monthlyPackageRank == "NONE") monthlyPackageRank = null
        if (packageRank == "NONE") packageRank = null
        if (newPackageRank == "NONE") newPackageRank = null

        if (!prefix.isNullOrEmpty()) {
            return parsePrefix(prefix)
        }

        val chosenRankKey = rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank
        if (chosenRankKey != null) {
            val plusColor = colors[rankPlusColor]
            val rankColor = colors[monthlyRankColor] ?: R.color.minecraft_gold
            return when (chosenRankKey) {
                "STAFF" -> makeRank("ዞ", R.color.minecraft_gold, null, null, R.color.minecraft_red)
                "YOUTUBER" -> makeRank("YOUTUBER", R.color.minecraft_white, null, null, R.color.minecraft_red)
                "SUPERSTAR" -> makeRank("MVP", rankColor, "++", plusColor)
                "MVP_PLUS" -> makeRank("MVP", R.color.minecraft_aqua, "+", plusColor)
                "MVP" -> makeRank("MVP", R.color.minecraft_aqua, null, null)
                "VIP_PlUS" -> makeRank("VIP", R.color.minecraft_green, "+", R.color.minecraft_gold)
                "VIP" -> makeRank("VIP", R.color.minecraft_green, null, null)
                else -> listOf(TagComponent(R.color.minecraft_gray, ""))
            }
        }
        return listOf(TagComponent(R.color.minecraft_gray, ""))
    }

    fun parsePrefix(tag: String?): List<TagComponent> {
        // TODO: Not yet implemented
        return listOf()
    }
}