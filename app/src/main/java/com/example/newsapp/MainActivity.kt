package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.widgets.category.CategoriesContent
import com.example.newsapp.widgets.DrawerBody
import com.example.newsapp.widgets.DrawerHeader
import com.example.newsapp.widgets.news.NEWS_ROUTE
import com.example.newsapp.widgets.news.NewsFragment
import kotlinx.coroutines.launch

const val CATEGORY_ROUTE = "categories"

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()


                ModalNavigationDrawer(drawerContent = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        DrawerHeader()
                        DrawerBody(navController) {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                }, drawerState = drawerState) {
                    Scaffold(topBar = { NewsAppBar(drawerState) }) {
                        NavHost(
                            navController = navController,
                            startDestination = CATEGORY_ROUTE,
                            modifier = Modifier.padding(top = it.calculateTopPadding())
                        )
                        {
                            composable(route = CATEGORY_ROUTE) {
                                CategoriesContent(navController)
                            }
                            composable(
                                route = NEWS_ROUTE,
                            ) {
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    NewsAppTheme {
        Scaffold {

        }
    }
}