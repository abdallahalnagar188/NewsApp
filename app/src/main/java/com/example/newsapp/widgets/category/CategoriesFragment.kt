package com.example.newsapp.widgets.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.CATEGORY_ROUTE
import com.example.newsapp.Constance
import com.example.newsapp.api.model.Category

@Composable
fun CategoriesContent(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Pick your category\n" +
                    "on interested",
            style = TextStyle(color = Color(0XFF4F5A69), fontSize = 20.sp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(6) {
                val item = Constance.categories.get(it)
                CategoryCard(item = item, position = it, navHostController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(item: Category, position: Int, navHostController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =
            colorResource(id = item.backGroundColor)
        ),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
        onClick = {
            navHostController.navigate("news/${item.apiID}"){
                popUpTo(CATEGORY_ROUTE){
                    inclusive = false
                }

            }

        },
        shape =
        if (position % 2 == 0)
            RoundedCornerShape(
                topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 0.dp
            )
        else
            RoundedCornerShape(
                topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 16.dp
            )

    ) {
        Image(
            painter = painterResource(id = item.drawableResId),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(120.dp)
                .width(80.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            stringResource(id = item.titleResId),
            style = TextStyle(color = Color.White),
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
    }
}