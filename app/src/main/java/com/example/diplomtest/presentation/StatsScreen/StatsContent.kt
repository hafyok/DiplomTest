package com.example.diplomtest.presentation.StatsScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavController
import kotlin.random.Random
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.diplomtest.ui.theme.DiplomTestTheme
import co.yml.charts.ui.linechart.LineChart



//@Preview
@Composable
fun StatsContent(navController: NavController) {
    val steps = 10

    val pointsList = getPointsList()
    val max = getMax(pointsList)
    val min = getMin(pointsList)
    Log.d("MyLog", "Max: $max Min: $min")
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsList.size - 1)
        .labelData { i -> i.toString() + "d" }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = (max - min) / steps.toFloat()
            String.format("%.1f", ((i * yScale) + min))
        }.build()
    DiplomTestTheme {
        val lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = pointsList,
                        LineStyle(color = Color.Green, width = 1.0f),
                        IntersectionPoint(color = Color.Blue, radius = 3.dp),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines(),
            backgroundColor = Color.White
        )

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    }
}

fun getPointsList(): List<co.yml.charts.common.model.Point> {
    val list = ArrayList<co.yml.charts.common.model.Point>()
    for (i in 0..31) {
        list.add(
            co.yml.charts.common.model.Point(
                i.toFloat(),
                Random.nextInt(50, 90).toFloat()
            )
        )
    }
    return list
}

private fun getMax(list: List<co.yml.charts.common.model.Point>): Float{
    var max = 0F
    list.forEach { point ->
        if(max < point.y) max = point.y
    }
    return max
}

private fun getMin(list: List<co.yml.charts.common.model.Point>): Float{
    var min = 100F
    list.forEach { point ->
        if(min > point.y) min = point.y
    }
    return min
}

////////////////////////////////////////////////////////////////////////////////////////////////////
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


