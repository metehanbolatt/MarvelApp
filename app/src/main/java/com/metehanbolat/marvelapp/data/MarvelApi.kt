package com.metehanbolat.marvelapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/marvel/{hero}")
    suspend fun getHero(@Path("hero") hero: String) : List<Marvel>
}