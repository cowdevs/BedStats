package com.example.bedstats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MojangService {
    @GET("name/{name}")
    fun queryUsername(@Path("name") name: String): Call<Player>
}