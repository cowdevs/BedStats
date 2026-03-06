package com.example.bedstats

import android.content.Context
import android.os.Parcelable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize

object RankFormatHelper {
	const val TAG = "RankFormatHelper"

	@Parcelize
	data class TagComponent(val color: Int, val text: String) : Parcelable

	fun makeRank(
		name: String,
		rankText: String,
		rankColor: Int,
		plusText: String?,
		plusColor: Int?,
		bracketColor: Int = rankColor
	): List<TagComponent> {
		return if (plusText != null && plusColor != null) {
			listOf(
				TagComponent(bracketColor, "["),
				TagComponent(rankColor, rankText),
				TagComponent(plusColor, plusText),
				TagComponent(bracketColor, "] $name")
			)
		} else {
			listOf(
				TagComponent(bracketColor, "["),
				TagComponent(rankColor, rankText),
				TagComponent(bracketColor, "] $name")
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

	fun calculateTag(playerData: PlayerData): List<TagComponent> {
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
			val p = colors[rankPlusColor]
			val r = colors[monthlyRankColor] ?: R.color.minecraft_gold
			return when (chosenRankKey) {
				"STAFF" -> makeRank(
					player.displayName,
					"ዞ",
					R.color.minecraft_gold,
					null,
					null,
					R.color.minecraft_red
				)

				"YOUTUBER" -> makeRank(
					player.displayName,
					"YOUTUBE",
					R.color.minecraft_white,
					null,
					null,
					R.color.minecraft_red
				)

				"SUPERSTAR" -> makeRank(
					player.displayName,
					"MVP",
					r,
					"++",
					p
				)

				"MVP_PLUS" -> makeRank(
					player.displayName,
					"MVP",
					R.color.minecraft_aqua,
					"+",
					p
				)

				"MVP" -> makeRank(
					player.displayName,
					"MVP",
					R.color.minecraft_aqua,
					null,
					null
				)

				"VIP_PLUS" -> makeRank(
					player.displayName,
					"VIP",
					R.color.minecraft_green,
					"+",
					R.color.minecraft_gold
				)

				"VIP" -> makeRank(
					player.displayName,
					"VIP",
					R.color.minecraft_green,
					null,
					null
				)

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
			val text = if (i == colorCodes.size - 1) {
				prefix.substring(code.range.last + 1)
			} else {
				prefix.substring(code.range.last + 1, colorCodes[i + 1].range.first)
			}
			Log.d(TAG, "parsePrefix: §$c, $text")
			components.add(TagComponent(colors[c] ?: R.color.minecraft_white, text))
		}

		return components
	}

	fun buildTextSpan(context: Context, tagComponents: List<TagComponent>): SpannableStringBuilder {
		val spannable = SpannableStringBuilder()
		for (component in tagComponents) {
			spannable.append(
				component.text,
				ForegroundColorSpan(ContextCompat.getColor(context, component.color)),
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE
			)
		}
		return spannable
	}
}