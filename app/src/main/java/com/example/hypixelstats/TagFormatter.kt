package com.example.hypixelstats

class TagFormatter {
    data class TagComponent(val colorCode: String, val text: String)

    /**
     * Ranks mapping translated from the JS file.
     * Each rank is a list of TagComponent(colorCode, text).
     */
    val ranks: Map<String, List<TagComponent>> = mapOf(
        "ADMIN" to listOf(TagComponent("c", "[ADMIN]")),
        "MODERATOR" to listOf(TagComponent("2", "[MOD]")),
        "HELPER" to listOf(TagComponent("9", "[HELPER]")),
        "JR_HELPER" to listOf(TagComponent("9", "[JR HELPER]")),
        "YOUTUBER" to listOf(TagComponent("c", "["), TagComponent("f", "YOUTUBE"), TagComponent("c", "]")),
        "SUPERSTAR" to listOf(TagComponent("%r", "[MVP"), TagComponent("%p", "++"), TagComponent("%r", "]")),
        "MVP_PLUS" to listOf(TagComponent("b", "[MVP"), TagComponent("%p", "+"), TagComponent("b", "]")),
        "MVP" to listOf(TagComponent("b", "[MVP]")),
        "VIP_PLUS" to listOf(TagComponent("a", "[VIP"), TagComponent("6", "+"), TagComponent("a", "]")),
        "VIP" to listOf(TagComponent("a", "[VIP]")),
        "DEFAULT" to listOf(TagComponent("7", ""))
    )

    /** Convert name-based colors to number-based strings (single char). */
    val colors: Map<String, String> = mapOf(
        "BLACK" to "0",
        "DARK_BLUE" to "1",
        "DARK_GREEN" to "2",
        "DARK_AQUA" to "3",
        "DARK_RED" to "4",
        "DARK_PURPLE" to "5",
        "GOLD" to "6",
        "GRAY" to "7",
        "DARK_GRAY" to "8",
        "BLUE" to "9",
        "GREEN" to "a",
        "AQUA" to "b",
        "RED" to "c",
        "LIGHT_PURPLE" to "d",
        "YELLOW" to "e",
        "WHITE" to "f"
    )

    private val defaultPlusColor = "c" // %p
    private val defaultRankColor = "6" // %r

    /**
     * Calculate the rank tag for the player object from the Hypixel API.
     * Assumes `Player` has properties:
     *  - packageRank: String?
     *  - newPackageRank: String?
     *  - monthlyPackageRank: String?
     *  - rankPlusColor: String?
     *  - monthlyRankColor: String?
     *  - rank: String?
     *  - prefix: String?
     *
     * Returns a list of TagComponent (colorCode, text).
     */
    fun calcTag(playerData: PlayerData): List<TagComponent> {
        val player = playerData.player

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
            return parseMinecraftTag(prefix)
        }

        val chosenRankKey = rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank
        if (chosenRankKey != null) {
            val base = ranks[chosenRankKey] ?: ranks["DEFAULT"]!!
            val p = colors[rankPlusColor] // nullable, may be null
            val r = colors[monthlyRankColor]
            return replaceCustomColors(base, p, r)
        }
        return replaceCustomColors(ranks["DEFAULT"]!!, null, null)
    }

    /**
     * Parse a tag string using Minecraft formatting codes (e.g. "§c[ADMIN]").
     * Returns a list of TagComponent(colorCode, text) similar to the ranks structure.
     */
    fun parseMinecraftTag(tag: String?): List<TagComponent> {
        if (tag == null) return listOf(TagComponent("f", ""))

        // Regex to find formatting codes like §c or §2 (capture the color char)
        val regex = Regex("§([a-f0-9])", RegexOption.IGNORE_CASE)
        val parts = mutableListOf<String>()

        var lastIndex = 0
        for (m in regex.findAll(tag)) {
            val start = m.range.first
            val endExclusive = m.range.last + 1
            // substring between previous index and start of this match
            parts.add(tag.substring(lastIndex, start))
            // the captured color char
            parts.add(m.groupValues[1])
            lastIndex = endExclusive
        }
        // remainder after last match
        parts.add(tag.substring(lastIndex))

        // JS code unshifted 'f' at the beginning (default white)
        parts.add(0, "f")

        // Build components: even indexes are color codes, odd indexes are text
        val components = mutableListOf<TagComponent>()
        var idx = 0
        while (idx < parts.size) {
            val color = parts[idx]
            val text = if (idx + 1 < parts.size) parts[idx + 1] else ""
            components.add(TagComponent(color, text))
            idx += 2
        }
        return components
    }

    /**
     * Replace wildcard color codes "%p" and "%r" in a rank list with the provided colors.
     * If p or r are null/invalid, defaults are applied.
     */
    fun replaceCustomColors(rank: List<TagComponent>, p: String?, r: String?): List<TagComponent> {
        // Deep copy the list (TagComponent is data class so .copy() suffices)
        val newRank = rank.map { it.copy() }.toMutableList()

        val plusColor = if (p != null && p.length == 1) p else defaultPlusColor
        val rankColor = if (r != null && r.length == 1) r else defaultRankColor

        for (i in newRank.indices) {
            val comp = newRank[i]
            when (comp.colorCode) {
                "%p" -> newRank[i] = comp.copy(colorCode = plusColor)
                "%r" -> newRank[i] = comp.copy(colorCode = rankColor)
            }
        }

        return newRank
    }

}