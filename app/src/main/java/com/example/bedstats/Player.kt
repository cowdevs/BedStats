package com.example.bedstats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Player(
    val name: String,
    val id: String
) : Parcelable
