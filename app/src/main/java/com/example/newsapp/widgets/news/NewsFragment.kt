package com.example.newsapp.widgets.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourceItem

const val NEWS_ROUTE = "news/{category}"

@Composable
fun NewsFragment(
    category: String?,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {


    viewModel.getNewsSources(category, viewModel.sourcesList)
    Column {
        NewsSourcesTabs(sourcesItemsList = viewModel.sourcesList.value, viewModel.newsList)
        NewsList(articlesItem = viewModel.newsList.value)
    }
}

@Composable
fun NewsSourcesTabs(
    sourcesItemsList: List<SourceItem>,
    newsResponseState: MutableState<List<ArticlesItem>>,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    if (sourcesItemsList.isNotEmpty())
        ScrollableTabRow(selectedTabIndex = viewModel.selectedIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}) {
            sourcesItemsList.forEachIndexed { index, sourcesItem ->
                if (viewModel.selectedIndex == index) {
                    viewModel.getNewsBySources(sourcesItem, newsResponseState)

                }
                Tab(selected = viewModel.selectedIndex == index,
                    onClick = {
                        viewModel.selectedIndex = index
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF39A552),
                    modifier = if (viewModel.selectedIndex == index) Modifier
                        .padding(end = 6.dp, top = 8.dp, bottom = 8.dp)
                        .background(
                            Color(0xFF39A552), RoundedCornerShape(50)
                        )
                    else Modifier
                        .padding(end = 6.dp, top = 8.dp, bottom = 8.dp)
                        .border(
                            2.dp, Color(0xFF39A552), RoundedCornerShape(50)
                        ),
                    text = { Text(text = sourcesItem.name ?: "") }
                )
            }
        }
}

@Composable
fun NewsList(articlesItem: List<ArticlesItem>) {
    LazyColumn {
        items(articlesItem.size) {
            NewsCard(articlesItem = articlesItem.get(it))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp).clickable {

            }
    ) {
        GlideImage(
            model = articlesItem.urlToImage ?: "", contentDescription = "News Picture",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp, 2.dp)
        )

        Text(
            text = articlesItem.author ?: "",
            style = TextStyle(color = Color.Gray), modifier = Modifier.padding(4.dp, 2.dp)
        )
        Text(
            text = articlesItem.title ?: "",
            style = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier.padding(4.dp, 2.dp)
        )
        Text(
            text = articlesItem.publishedAt ?: "",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .align(Alignment.End)
                .padding(4.dp, 2.dp)
        )
    }
}