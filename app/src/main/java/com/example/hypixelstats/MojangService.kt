package com.example.hypixelstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MojangService {
    @GET("users/profiles/minecraft/{user}")
    fun queryUsername(@Path("user") user: String?): Call<Player>
}