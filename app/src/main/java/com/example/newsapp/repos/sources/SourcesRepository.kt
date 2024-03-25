package com.example.newsapp.repos.sources

import com.example.newsapp.api.model.SourceItem

interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourceItem>
}

interface OnlineSourcesDataSource {
    suspend fun getSourcesFromApi(categoryId: String): List<SourceItem>
}

interface OfflineSourcesDataSource {
    suspend fun getSourcesFromDB(): List<SourceItem>
    suspend fun saveSourcesIntoDB(list: List<SourceItem>)
}