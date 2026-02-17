package com.example.hypixelstats

import okhttp3.OkHttpClient
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
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("API-Key", BuildConfig.HYPIXEL_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_HYPIXEL_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}