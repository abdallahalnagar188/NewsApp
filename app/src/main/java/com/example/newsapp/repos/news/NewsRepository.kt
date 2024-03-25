package com.example.newsapp.repos.news

import com.example.newsapp.api.model.ArticlesItem

interface NewsRepository {
    suspend fun getNewsData(sourceId: String): List<ArticlesItem>
}

interface NewsOnlineDataSource {
    suspend fun getNewsFromApi(sourceId: String): List<ArticlesItem>
}

interface NewsOfLineDataSource {
    suspend fun getNewsFromDB(): List<ArticlesItem>
    suspend fun saveNewsToDB(newsList: List<ArticlesItem>)
}