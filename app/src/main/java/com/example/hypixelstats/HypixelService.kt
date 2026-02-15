package com.example.hypixelstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface HypixelService {
    @Headers("API-Key: 6904e1f3-95b4-4d55-80e6-14e7609618a8")
    @GET("player")
    fun getHypixelPlayerData(@Query("uuid") uuid: String): Call<PlayerData>
}