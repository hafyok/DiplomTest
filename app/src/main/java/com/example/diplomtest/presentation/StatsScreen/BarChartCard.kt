package com.example.diplomtest.presentation.StatsScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import com.example.diplomtest.ui.theme.DiplomTestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BarChartCard(viewModel: StatsViewModel) {
    DiplomTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val scope = rememberCoroutineScope()
            val maxRange = 60
            var barData by remember {
                mutableStateOf(
                    listOf(
                        BarData(
                            Point(0f, 0f), // Инициализация, чтобы проверить, что график work
                        )
                    )
                )
            }
            var xAxisData by remember {
                mutableStateOf(AxisData.Builder().build())
            }
            var yAxisData by remember {
                mutableStateOf(AxisData.Builder().build())
            }
            val yStepSize = 10

            LaunchedEffect(true) {
                scope.launch(Dispatchers.IO) {
                    viewModel.getBarChartData().let { points ->
                        barData = points
                    }
                }
            }

            LaunchedEffect(key1 = true) {

                xAxisData = AxisData.Builder()
                    .axisStepSize(30.dp)
                    .steps(barData.size - 1)
                    .bottomPadding(40.dp)
                    .axisLabelAngle(20f)
                    .startDrawPadding(48.dp)
                    .labelData { index -> barData[index].label }
                    .build()
                yAxisData = AxisData.Builder()
                    .steps(yStepSize)
                    .labelAndAxisLinePadding(20.dp)
                    .axisOffset(20.dp)
                    .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                    .build()
            }

            val barChartData = BarChartData(
                chartData = barData,
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                barStyle = BarStyle(
                    paddingBetweenBars = 20.dp,
                    barWidth = 25.dp
                ),
                showYAxis = true,
                showXAxis = true,
                horizontalExtraSpace = 10.dp,
            )
            BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
        }
    }
}