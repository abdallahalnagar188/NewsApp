package com.example.newsapp.api

import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    suspend fun getNewsSources(
         @Query("apiKey") apiKey:String
        ,@Query("category")category: String?
    ) :SourceResponse

    @GET("everything")
    suspend fun getNewsBySources(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String?
    ): NewsResponse
}