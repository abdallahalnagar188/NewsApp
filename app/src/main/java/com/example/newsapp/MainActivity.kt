package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.api.model.SourceItem
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.widgets.CategoriesContent
import com.example.newsapp.widgets.DrawerBody
import com.example.newsapp.widgets.DrawerHeader
import com.example.newsapp.widgets.NEWS_ROUTE
import com.example.newsapp.widgets.NewsFragment
import kotlinx.coroutines.launch

const val CATEGORY_ROUTE = "categories"

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(drawerContent = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DrawerHeader()
                        DrawerBody()
                    }
                }, drawerState = drawerState) {
                    Scaffold(topBar = { NewsAppBar(drawerState) }) {
//                        NewsSourcesTabs(sourcesItemsList = sourcesList.value)
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = CATEGORY_ROUTE,
                            modifier = Modifier.padding(top = it.calculateTopPadding()))
                        {
                            composable(route = CATEGORY_ROUTE){
                                CategoriesContent(navController)
                            }
                            composable(
                                route = NEWS_ROUTE,
                            ){
                                val argument = it.arguments?.getString("category")
                                NewsFragment(argument)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.news),
            style = TextStyle(color = Color.White, fontSize = 22.sp)
        )
    }, modifier = Modifier.clip(
        RoundedCornerShape(
            topStart = 0.dp, topEnd = 0.dp, bottomStart = 30.dp, bottomEnd = 30.dp
        )
    ), colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color(0xFF39A552), navigationIconContentColor = Color.White
    ), navigationIcon = {
        IconButton(onClick = {
            scope.launch { drawerState.open() }

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
    if (sourcesItemsList.isNotEmpty()) ScrollableTabRow(selectedTabIndex = selectedIndex,
        containerColor = Color.Transparent,
        divider = {},
        indicator = {}) {
        sourcesItemsList.forEachIndexed { index, sourcesItem ->
            Tab(selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color(0xFF39A552),
                modifier = if (selectedIndex == index) Modifier
                    .padding(end = 6.dp, top = 8.dp, bottom = 8.dp)
                    .background(
                        Color(0xFF39A552), RoundedCornerShape(50)
                    )
                else Modifier
                    .padding(end = 6.dp,top = 8.dp, bottom = 8.dp)
                    .border(
                        2.dp, Color(0xFF39A552), RoundedCornerShape(50)
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
        Scaffold {
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