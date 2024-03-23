package com.example.newsapp

import android.app.Application
import com.example.newsapp.database.NewsLocalDatabase

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsLocalDatabase.init(this)
    }
}