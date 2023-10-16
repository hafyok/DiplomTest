package com.example.diplomtest.View.StatsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun StatsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Верхний и нижний отступ
            .wrapContentSize(Alignment.TopCenter), // Выравнивание по верхнему центру
        //verticalArrangement = Arrangement.Top, // Выравнивание элементов по верхнему краю
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Statistics screen",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 25.sp),
        )
        Card(
            modifier = Modifier.fillMaxWidth(),

            //backgroundColor = BlueLight,

        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ){
                Text(
                    text = "Text for testing",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 25.sp),
                )
                Text(
                    text = "Text for testing",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 25.sp),
                )
                Text(
                    text = "Text for testing",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 25.sp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Second text for testing",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 25.sp),
            )
        }


    }

}