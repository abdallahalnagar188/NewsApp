package com.example.newsapp.repos.sources

import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.repos.NetworkHandler

class SourcesRepositoryImp(
    val onlineDataSource: OnlineSourcesDataSource,
    val offLineDataSource: OfflineSourcesDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourceItem> {
        return try {
            if (networkHandler.isOnline()) {
                val sourcesList = onlineDataSource.getSourcesFromApi(categoryId)
                offLineDataSource.saveSourcesIntoDB(sourcesList)
                sourcesList
            } else
                offLineDataSource.getSourcesFromDB()
        } catch (ex: Exception) {
            throw ex
        }
    }
}