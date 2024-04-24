package com.example.diplomtest.presentation.StatsScreen

import android.app.Application
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LineChartCard() {
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
                String.format("%.0f", ((i * yScale) + min))
            }.build()
    }

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

    co.yml.charts.ui.linechart.LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), lineChartData = lineChartData
    )



}
fun getMax(list: List<Point>): Float {
    var max = 0F
    list.forEach { point ->
        if (max < point.y) max = point.y
    }
    return max
}

fun getMin(list: List<Point>): Float {
    var min = 100F
    list.forEach { point ->
        if (min > point.y) min = point.y
    }
    return min
}