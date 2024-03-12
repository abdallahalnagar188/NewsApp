package com.example.newsapp.widgets.news_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.api.model.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsPage(
    title: String,
    author: String,
    urlToImage: String,
) {
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            GlideImage(
                model = urlToImage,
                contentDescription = "News Picture",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp, 2.dp)
            )

            Text(
                text = author ?: "",
                style = TextStyle(color = Color.Gray), modifier = Modifier.padding(4.dp, 2.dp)
            )
            Text(
                text = title ?: "",
                style = TextStyle(color = Color.Black, fontSize = 18.sp),
                modifier = Modifier.padding(4.dp, 2.dp)
            )
//            Text(
//                text = publishedAt ?: "",
//                style = TextStyle(color = Color.Gray),
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(4.dp, 2.dp)
//            )
        }
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
//        Text(
//            text = description ?: "",
//            style = TextStyle(color = Color.Black),
//            modifier = Modifier
//                .align(Alignment.Center)
//                .padding(4.dp, 2.dp)
//        )
    }

}