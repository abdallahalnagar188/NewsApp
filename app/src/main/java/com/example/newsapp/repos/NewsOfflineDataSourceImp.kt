package com.example.newsapp.repos

import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.database.NewsDao

class NewsOfflineDataSourceImp(val newsDao: NewsDao) : NewsOfLineDataSource {
    override suspend fun getNewsFromDB(): List<ArticlesItem> {
        try {
            return newsDao.getNewsFromDB()
        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun saveNewsToDB(newsList: List<ArticlesItem>) {
        TODO("Not yet implemented")
    }
}