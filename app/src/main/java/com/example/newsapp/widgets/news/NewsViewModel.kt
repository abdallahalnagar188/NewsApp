package com.example.newsapp.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constance
import com.example.newsapp.api.APIManager
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourceItem
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val sourcesList = mutableStateOf<List<SourceItem>>(listOf())

    val newsList = mutableStateOf<List<ArticlesItem>>(listOf())
    var selectedIndex = mutableIntStateOf(0)


    fun getNewsBySources(
        sourcesItem: SourceItem, newsResponseState: MutableState<List<ArticlesItem>>
    ) {
        //Concurrency
        try {
            viewModelScope.launch {
                val response = APIManager.getNewsServices()
                    .getNewsBySources(Constance.API_KEY, sourcesItem.id ?: "")
                newsResponseState.value = response.articles ?: listOf()
            }
        } catch (ex: Exception) {
            Log.e("ex:", "${ex.message}")
        }

//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>, response: Response<NewsResponse>
//                ) {
//                    val newsResponse = response.body()
//                    newsResponseState.value = newsResponse?.articles!!
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//                }
//
//            })
    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourceItem>>) {
        try {
            viewModelScope.launch {
                val response = APIManager.getNewsServices()
                    .getNewsSources(Constance.API_KEY, category = category ?: "")
                sourcesList.value = (response.sources ?: listOf()) as List<SourceItem>

            }
        } catch (ex: Exception) {
            Log.e("EX: ", "${ex.message}")
        }

//            .enqueue(object : Callback<SourceResponse> {
//                override fun onResponse(
//                    call: Call<SourceResponse>, response: Response<SourceResponse>
//                ) {
//                    val body = response.body()
////                Log.e("TAG", "onResponse: ${body?.status}")
////                Log.e("TAG", "onResponse: ${body?.sources}")
//                    sourcesList.value = (body?.sources ?: listOf()) as List<SourceItem>
//                }
//
//                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
//
//                }
//
//            })
    }
}
