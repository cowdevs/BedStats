package com.example.hypixelstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface HypixelService {
    @Headers("API-Key: e436b77e-39f1-4f13-b985-c6390af7b946")
    @GET("player")
    fun getHypixelPlayerData(@Query("uuid") uuid: String): Call<PlayerData>
}