package com.example.hypixelstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface HypixelService {
    @Headers("API-Key: cdfa668d-5a55-4e05-99d3-be6637b76e7b")
    @GET("player")
    fun getHypixelPlayerData(@Query("uuid") uuid: String): Call<PlayerData>
}