package com.example.diplomtest.View.StatsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun StatsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Statistics",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        NumberList()

        CategoriesDropdown()

        Spacer(modifier = Modifier.height(16.dp))

        StatisticCard(
            title = "Stat 1",
            data = listOf(10, 20, 30, 25, 35),
            color = Color(0xFF0077C0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        StatisticCard(
            title = "Stat 2",
            data = listOf(40, 35, 45, 50, 55),
            color = Color(0xFF00A080)
        )

        Spacer(modifier = Modifier.height(16.dp))

        StatisticCard(
            title = "Stat 3",
            data = listOf(15, 10, 20, 30, 40),
            color = Color(0xFF9B59B6)
        )
    }
}

@Composable
fun StatisticCard(title: String, data: List<Int>, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Реализуйте график с использованием библиотеки MPAndroidChart.
            // Вставьте здесь ваш код для графика, используя данные из списка `data`.
        }
    }
}

@Preview
@Composable
fun NumberList() {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) { number ->
            NumberItem(number = number + 1)
        }
    }
}

@Composable
fun NumberItem(number: Int) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        //elevation = 4.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = number.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}



@Composable
fun CategoriesDropdown() {
    // Реализуйте компонент для выбора категорий
    // в соответствии с вашими требованиями.
}

@Composable
fun StatisticsCard() {
    // Реализуйте карточку со статистикой
    // в соответствии с вашими данными.
}

@Composable
fun LineChart() {
    // Реализуйте график (например, с использованием библиотеки MPAndroidChart)
    // и отобразите данные, которые вам необходимы.
}


