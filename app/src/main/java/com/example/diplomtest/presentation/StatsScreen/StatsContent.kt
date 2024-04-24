package com.example.diplomtest.presentation.StatsScreen

import android.app.Application
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.ui.theme.DiplomTestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//@Preview
@Composable
fun StatsContent(navController: NavController) {
    val application = Application()
    val viewModel = StatsViewModel(AppDatabase.getDatabase(application).timerSessionDao())
    val scope = rememberCoroutineScope()
    val steps = 10

    var pointsList by remember {
        mutableStateOf(listOf(Point(0f, 0f)))
    }
    var max by remember {
        mutableStateOf(0f)
    }
    var min by remember {
        mutableStateOf(0f)
    }
    var xAxisData by remember {
        mutableStateOf(AxisData.Builder().build())
    }
    var yAxisData by remember {
        mutableStateOf(AxisData.Builder().build())
    }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            viewModel.getPointsList().let { points ->
                pointsList = points
            }
            max = getMax(pointsList)
            min = getMin(pointsList)
        }
    }

    LaunchedEffect(key1 = true) {

        /*val max = getMax(pointsList)
        val min = getMin(pointsList)
        Log.d("MaxMin", "Max: $max Min: $min")*/
        xAxisData = AxisData.Builder()
            .axisStepSize(50.dp)
            .backgroundColor(Color.Transparent)
            .steps(pointsList.size - 1)
            .labelData { i -> i.toString() + "d" }
            .labelAndAxisLinePadding(15.dp)
            .build()

        yAxisData = AxisData.Builder()
            .steps(steps)
            .backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i ->
                val yScale = (max - min) / steps.toFloat()
                String.format("%.1f", ((i * yScale) + min))
            }.build()
    }

    /*LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            Log.d("SESSIONS", viewModel.getDurationPlan().joinToString(", "))
        }
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    .height(300.dp), lineChartData = lineChartData
            )
        }

    }
}

private fun getMax(list: List<Point>): Float {
    var max = 0F
    list.forEach { point ->
        if (max < point.y) max = point.y
    }
    return max
}

private fun getMin(list: List<Point>): Float {
    var min = 100F
    list.forEach { point ->
        if (min > point.y) min = point.y
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
                text = title, style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ), modifier = Modifier.padding(bottom = 16.dp)
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
        contentPadding = PaddingValues(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                text = number.toString(), style = TextStyle(
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


