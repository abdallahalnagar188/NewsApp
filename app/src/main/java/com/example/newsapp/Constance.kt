package com.example.newsapp

import com.example.newsapp.api.model.Category

object Constance {
    val API_KEY = "966bf2efeb3b467b97aee5316c055519"
    val categories = listOf(
        Category(
            "sports",R.drawable.ball,R.string.sports,R.color.red
        ),Category(
            "politics",R.drawable.politics,R.string.politics,R.color.blue
        ),Category(
            "health",R.drawable.health,R.string.health,R.color.bink
        ),Category(
            "business",R.drawable.bussines,R.string.business,R.color.bony
        ),Category(
            "entertainment",R.drawable.environment,R.string.enviroment,R.color.light_blue
        ),Category(
            "science",R.drawable.science,R.string.science,R.color.yellow
        ),
    )
}