package com.example.newsapp.widgets

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@Composable
fun DrawerHeader() {
    Text(
        text = stringResource(id = R.string.app_name),
        modifier = Modifier
            .fillMaxWidth(0.75F)
            .background(Color(0xFF39A552), RoundedCornerShape(0.dp))
            .padding(vertical = 20.dp),

        style = TextStyle(color = Color.White, fontSize = 20.sp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun DrawerBody() {
    Column(modifier = Modifier
        .fillMaxWidth(0.75F)
        .fillMaxHeight()
        .background(Color.White)) {
        Spacer(modifier = Modifier.padding(12.dp))
        NewsDrawerItem(iconId = R.drawable.ico_catigores, textId = R.string.categories)
        Spacer(modifier = Modifier.padding(12.dp))
        NewsDrawerItem(iconId = R.drawable.ico_settings, textId = R.string.settings)
    }
}

@Composable
fun NewsDrawerItem(iconId: Int, textId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
    {
        Spacer(modifier = Modifier.width(10.dp))
        Icon(painter = painterResource(id = iconId), contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = textId), style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            )
        )
    }
}