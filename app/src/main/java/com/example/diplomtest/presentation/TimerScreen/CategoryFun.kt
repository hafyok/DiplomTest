package com.example.diplomtest.presentation.TimerScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryFun() {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    LazyRow(
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        items(Category.categoryList) { category ->
            val isSelected = selectedCategory == category
            val backColor = if (isSelected) Color.DarkGray else Color.Gray

            androidx.compose.material.Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        selectedCategory = category
                        Category.updateCategory(category)
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = backColor
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = category,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
