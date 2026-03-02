package com.example.bedstats

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat

object RankFormatHelper {
	const val TAG = "RankFormatHelper"

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

	fun calcTag(stats: Statistics): List<TagComponent> {
		var packageRank = stats.packageRank
		var newPackageRank = stats.newPackageRank
		var monthlyPackageRank = stats.monthlyPackageRank
		val rankPlusColor = stats.rankPlusColor
		val monthlyRankColor = stats.monthlyRankColor
		var rank = stats.rank
		val prefix = stats.prefix

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
				"YOUTUBER" -> makeRank(
					"YOUTUBER",
					R.color.minecraft_white,
					null,
					null,
					R.color.minecraft_red
				)

				"SUPERSTAR" -> makeRank("MVP", rankColor, "++", plusColor)
				"MVP_PLUS" -> makeRank("MVP", R.color.minecraft_aqua, "+", plusColor)
				"MVP" -> makeRank("MVP", R.color.minecraft_aqua, null, null)
				"VIP_PLUS" -> makeRank("VIP", R.color.minecraft_green, "+", R.color.minecraft_gold)
				"VIP" -> makeRank("VIP", R.color.minecraft_green, null, null)
				else -> listOf(TagComponent(R.color.minecraft_gray, ""))
			}
		}
		return listOf(TagComponent(R.color.minecraft_gray, ""))
	}

	fun parsePrefix(prefix: String): List<TagComponent> {
		val components = mutableListOf<TagComponent>()

		val regex = Regex("§([a-f0-9])", RegexOption.IGNORE_CASE)
		val colorCodes = regex.findAll(prefix).toList()

		for (i in colorCodes.indices) {
			val code = colorCodes[i]
			val c = code.groupValues[1]

			var text: String
			if (i == colorCodes.size - 1) {
				text = prefix.substring(code.range.last + 1)
			} else {
				text = prefix.substring(code.range.last + 1, colorCodes[i + 1].range.first)
			}
			Log.d(TAG, "parsePrefix: §$c, $text")
			components.add(TagComponent(colors[c] ?: R.color.minecraft_white, text))
		}

		return components
	}

	fun buildTextSpan(context: Context, tags: List<TagComponent>): SpannableStringBuilder {
		val e = SpannableStringBuilder()

		for (tag in tags) {
			val spannable = SpannableString(tag.text)
			spannable.setSpan(
				ForegroundColorSpan(ContextCompat.getColor(context, tag.color)),
				0, spannable.length,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
			)
			e.append(spannable)
		}

		return e
	}
}