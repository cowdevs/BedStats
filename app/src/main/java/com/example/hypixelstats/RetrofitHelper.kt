package com.example.hypixelstats

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    const val BASE_URL_MOJANG_API = "https://api.minecraftservices.com/minecraft/profile/lookup/"
    const val BASE_URL_HYPIXEL_API = "https://api.hypixel.net/v2/"

    fun getInstanceMojangAPI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MOJANG_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstanceHypixelAPI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_HYPIXEL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}