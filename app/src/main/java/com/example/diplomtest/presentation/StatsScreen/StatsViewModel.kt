package com.example.diplomtest.presentation.StatsScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import com.example.diplomtest.data.database.TimerSessionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random

class StatsViewModel(private val db: TimerSessionDao): ViewModel() {
    var data = db.getAllItems()
    private var pointsList = listOf<Point>()

    /*private val repository = TimerRepositoryImpl()

    private val getTimerUseCase = GetTimerUseCase()*/
    init {
        data = db.getAllItems()
        viewModelScope.launch(Dispatchers.IO){
            pointsList = getPointsListFromDB()
        }
    }

    suspend fun getAllDates(): List<Date> {
        return db.getAllDates()
    }

    private suspend fun getDurationPlan(): List<Int>{
        return db.getSessions()
    }

    private suspend fun getPointsListFromDB(): List<Point>{
        val sessionsDB = getDurationPlan()
        val list = ArrayList<Point>()
        sessionsDB.forEachIndexed { index, session ->
            list.add(
                Point(
                    index.toFloat(),
                    session.toFloat()
                )
            )
        }

        return list
    }

    fun getPointsList(): List<Point>{
        return pointsList
    }

    fun getBarChartData(
        listSize: Int = 7,
        maxRange: Int = 60,
        barChartType: BarChartType = BarChartType.VERTICAL,
        dataCategoryOptions: DataCategoryOptions = DataCategoryOptions()
    ): List<BarData> {
        val list = arrayListOf<BarData>()
        for (index in 0 until listSize) {
            val point = when (barChartType) {
                BarChartType.VERTICAL -> {
                    Point(
                        index.toFloat(),
                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat()
                    )
                }

                BarChartType.HORIZONTAL -> {
                    Point(
                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat(),
                        index.toFloat()
                    )
                }
            }

            list.add(
                BarData(
                    point = point,
                    color = Color(
                        Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                    ),
                    dataCategoryOptions = dataCategoryOptions,
                    label = "Bar$index",
                )
            )
        }
        return list
    }
}