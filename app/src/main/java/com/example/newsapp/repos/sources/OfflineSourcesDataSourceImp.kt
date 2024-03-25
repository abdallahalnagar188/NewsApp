package com.example.newsapp.repos.sources

import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.database.SourcesDao

class OfflineSourcesDataSourceImp(val sourcesDao: SourcesDao) : OfflineSourcesDataSource {
    override suspend fun getSourcesFromDB(): List<SourceItem> {
        try {
            return sourcesDao.getSourcesFromDB()

        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun saveSourcesIntoDB(list: List<SourceItem>) {
        try {
            sourcesDao.insertSourcesToDB(list)
        } catch (ex: Exception) {
            throw ex
        }
    }
}