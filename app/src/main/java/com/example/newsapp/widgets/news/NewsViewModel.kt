package com.example.newsapp.widgets.news

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.Constance
import com.example.newsapp.api.APIManager
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.api.model.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val sourcesList = mutableStateOf<List<SourceItem>>(listOf())
    val newsList = mutableStateOf<List<ArticlesItem>>(listOf())
    var selectedIndex by mutableIntStateOf(0)


    fun getNewsBySources(
        sourcesItem: SourceItem, newsResponseState: MutableState<List<ArticlesItem>>
    ) {
        APIManager.getNewsServices().
        getNewsBySources(Constance.API_KEY, sourcesItem.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>, response: Response<NewsResponse>
                ) {
                    val newsResponse = response.body()
                    newsResponseState.value = newsResponse?.articles!!
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }

            })
    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourceItem>>) {
        APIManager.getNewsServices().getNewsSources(Constance.API_KEY, category = category ?: "")

            .enqueue(object : Callback<SourceResponse> {
                override fun onResponse(
                    call: Call<SourceResponse>, response: Response<SourceResponse>
                ) {
                    val body = response.body()
//                Log.e("TAG", "onResponse: ${body?.status}")
//                Log.e("TAG", "onResponse: ${body?.sources}")
                    sourcesList.value = (body?.sources ?: listOf()) as List<SourceItem>
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {

                }

            }
            )
    }
}
