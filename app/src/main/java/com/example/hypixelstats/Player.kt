package com.example.hypixelstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Player(
    val name: String,
    val id: String
) : Parcelable
