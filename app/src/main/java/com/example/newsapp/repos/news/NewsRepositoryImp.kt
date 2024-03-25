package com.example.newsapp.repos.news

import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.repos.NetworkHandler

class NewsRepositoryImp(
    val onlineDataSource: NewsOnlineDataSource,
    val ofLineDataSource: NewsOfLineDataSource,
    val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getNewsData(sourceId: String): List<ArticlesItem> {
        return try {
            if (networkHandler.isOnline()) {
                val newsList = onlineDataSource.getNewsFromApi(sourceId)
                ofLineDataSource.saveNewsToDB(newsList)
                newsList
            } else
                ofLineDataSource.getNewsFromDB()
        } catch (ex: Exception) {
            throw ex
        }

    }
}