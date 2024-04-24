package com.example.diplomtest.presentation.StatsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.common.utils.DataUtils.getGroupBarChartData
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.GroupBarChartData
import com.example.diplomtest.ui.theme.DiplomTestTheme

@Preview
@Composable
fun BarChartCard() {
    DiplomTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val barSize = 3
            val groupBarData = getGroupBarChartData(50, 50, barSize)
            val colorPaletteList = DataUtils.getColorPaletteList(barSize)
            val groupBarPlotData = BarPlotData(
                groupBarList = groupBarData,
                barColorPaletteList = colorPaletteList
            )
            val yStepSize = 10
            val xAxisData = co.yml.charts.axis.AxisData.Builder()
                .axisStepSize(30.dp)
                .steps(groupBarData.size - 1)
                .bottomPadding(10.dp)
                .labelData { index -> groupBarData[index].label }
                .build()
            val yAxisData = co.yml.charts.axis.AxisData.Builder()
                .steps(yStepSize)
                .labelAndAxisLinePadding(20.dp)
                .axisOffset(20.dp)
                .labelData { index -> (index * (50 / yStepSize)).toString() }
                .build()
            val legendsConfig = LegendsConfig(
                DataUtils.getLegendsLabelData(colorPaletteList),
                gridColumnCount = 3
            )
            val groupBarChartData = GroupBarChartData(
                barPlotData = groupBarPlotData,
                xAxisData = xAxisData,
                yAxisData = yAxisData
            )
            Column {
                GroupBarChart(
                    modifier = Modifier
                        .height(400.dp),
                    groupBarChartData = groupBarChartData
                )
                Legends(legendsConfig = legendsConfig)
            }
        }
    }
}