package com.example.diplomtest.presentation.StatsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.diplomtest.data.database.TimerSessionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

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
}