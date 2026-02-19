package com.example.bedstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface HypixelService {
    @GET("player")
    fun getHypixelPlayerData(@Query("uuid") uuid: String): Call<PlayerData>
}