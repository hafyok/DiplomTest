package com.example.diplomtest.View.TimerScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CategoryFun() {
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(Category.categoryList) { category ->
            androidx.compose.material.Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        Category.currentCategory = category
                        Log.d("TestCategory", Category.currentCategory)
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.Gray // Замените этот цвет на желаемый фон кнопки
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = category,
                        color = Color.White, // Замените этот цвет на желаемый цвет текста
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
