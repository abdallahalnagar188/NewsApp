package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourceItem

@Database(entities = [ArticlesItem::class, SourceItem::class], version = 1)
abstract class NewsLocalDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getSourcesDao(): SourcesDao

    companion object {
        var DATABASE: NewsLocalDatabase? = null
        fun init(context: Context) {
            DATABASE =
                Room.databaseBuilder(context, NewsLocalDatabase::class.java, "News Local DB")
                    .fallbackToDestructiveMigration().build()

        }

        fun getInstance(): NewsLocalDatabase {
            return DATABASE!!
        }
    }
}