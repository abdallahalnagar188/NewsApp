package com.example.newsapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    fun getNewsSources(@Query("apiKey") apiKey:String)
}