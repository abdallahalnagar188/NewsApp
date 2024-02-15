package com.example.newsapp

import android.annotation.SuppressLint
import android.graphics.Paint.Style
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.api.APIManager
import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.api.model.SourceResponse
import com.example.newsapp.ui.theme.NewsAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    val API_KEY = "966bf2efeb3b467b97aee5316c055519"

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                var sourcesList: MutableState<List<SourceItem>> =
                    remember {
                        mutableStateOf(listOf())
                    }
                APIManager.getNewsServices().getNewsSources(API_KEY)
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

                    }
                    )

                Scaffold(topBar = { NewsAppBar() }) {

                }
                NewsSourcesTabs(sourcesItemsList = sourcesList.value)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar() {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.news),
            style = TextStyle(color = Color.White, fontSize = 22.sp)
        )
    },
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        ), colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF39A552),
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_menu),
                    contentDescription = "Icon Menu"
                )
            }
        }

    )
}


@Composable
fun NewsSourcesTabs(
    sourcesItemsList: List<SourceItem>
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    if (sourcesItemsList.isNotEmpty())
        ScrollableTabRow(selectedTabIndex = selectedIndex, containerColor = Color.Transparent,
            divider = {},
            indicator = {}
        ) {
            sourcesItemsList.forEachIndexed { index, sourcesItem ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    }, selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF39A552),
                    modifier = if (selectedIndex == index)
                        Modifier
                            .padding(end = 2.dp)
                            .background(
                                Color(0xFF39A552),
                                RoundedCornerShape(50)
                            )
                    else Modifier
                        .padding(end = 2.dp)
                        .border(
                            2.dp, Color(0xFF39A552),
                            RoundedCornerShape(50)
                        ),
                    text = { Text(text = sourcesItem.name ?: "") }

                )
            }
        }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    NewsAppTheme {
        Scaffold(topBar = { NewsAppBar() }) {
            NewsSourcesTabs(
                sourcesItemsList = listOf(
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                    SourceItem(name = "abdallah"),
                )
            )
        }
    }
}