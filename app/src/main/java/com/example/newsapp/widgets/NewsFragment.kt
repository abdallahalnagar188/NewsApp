package com.example.newsapp.widgets

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.newsapp.Constance
import com.example.newsapp.NewsSourcesTabs
import com.example.newsapp.api.APIManager
import com.example.newsapp.api.model.Category
import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.api.model.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val NEWS_ROUTE = "news"
@Composable
fun NewsFragment(category:String?) {
    var sourcesList = remember {
        mutableStateOf<List<SourceItem>>(listOf())
    }
    getNewsSources(category,sourcesList)
    Column {
        NewsSourcesTabs(sourcesItemsList = sourcesList.value)
    }
}
fun getNewsSources(category:String?,sourcesList : MutableState<List<SourceItem>>){
    APIManager.
    getNewsServices().
    getNewsSources( Constance.API_KEY,category = category?:"")

        .enqueue(object : Callback<SourceResponse> {
            override fun onResponse(
                call: Call<SourceResponse>, response: Response<SourceResponse>
            ) {
                val body = response.body()
                Log.e("TAG", "onResponse: ${body?.status}")
                Log.e("TAG", "onResponse: ${body?.sources}")
                sourcesList.value = (body?.sources ?: listOf()) as List<SourceItem>
            }

            override fun onFailure(call: Call<SourceResponse>, t: Throwable) {

            }

        })

}