package com.example.newsapp.repos.news

import com.example.newsapp.Constance
import com.example.newsapp.api.NewsServices
import com.example.newsapp.api.model.ArticlesItem

class NewsOnlineDataSourceImp(val newsServices: NewsServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromApi(sourceId: String): List<ArticlesItem> {
        try {
            return newsServices.getNewsBySources(Constance.API_KEY, sourceId).articles ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }

    }
}