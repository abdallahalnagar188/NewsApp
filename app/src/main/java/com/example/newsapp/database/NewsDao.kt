package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.api.model.ArticlesItem

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNewsToDB(list: List<ArticlesItem>)
    @Query("Select * From ArticlesItem")
    suspend fun getNewsFromDB():List<ArticlesItem>
}