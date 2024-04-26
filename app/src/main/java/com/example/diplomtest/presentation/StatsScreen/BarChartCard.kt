package com.example.diplomtest.presentation.StatsScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarStyle
import com.example.diplomtest.ui.theme.DiplomTestTheme

@Composable
fun BarChartCard(viewModel: StatsViewModel) {
    DiplomTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val maxRange = 50
            val barData = DataUtils.getBarChartData(7, maxRange, BarChartType.VERTICAL, DataCategoryOptions())
            val yStepSize = 10

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .steps(barData.size - 1)
                .bottomPadding(40.dp)
                .axisLabelAngle(20f)
                .startDrawPadding(48.dp)
                .labelData { index -> barData[index].label }
                .build()
            val yAxisData = AxisData.Builder()
                .steps(yStepSize)
                .labelAndAxisLinePadding(20.dp)
                .axisOffset(20.dp)
                .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                .build()
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