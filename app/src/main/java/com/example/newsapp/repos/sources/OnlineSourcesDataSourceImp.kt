package com.example.newsapp.repos.sources

import com.example.newsapp.Constance
import com.example.newsapp.api.NewsServices
import com.example.newsapp.api.model.SourceItem

class OnlineSourcesDataSourceImp(val newsServices: NewsServices) : OnlineSourcesDataSource {
    override suspend fun getSourcesFromApi(categoryId: String): List<SourceItem> {
        try {
            return (newsServices.getNewsSources(Constance.API_KEY, categoryId).sources?: listOf()) as List<SourceItem>
        } catch (ex: Exception) {
            throw ex
        }
    }
}