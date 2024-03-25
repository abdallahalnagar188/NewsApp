package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourceItem

@Dao
interface SourcesDao {
    @Insert
    suspend fun insertSourcesToDB(list: List<SourceItem>)
    @Query("Select * From SourceItem")
    suspend fun getSourcesFromDB():List<SourceItem>
}