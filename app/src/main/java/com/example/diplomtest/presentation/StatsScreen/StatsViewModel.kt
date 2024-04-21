package com.example.diplomtest.presentation.StatsScreen

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.diplomtest.data.database.TimerSessionDao
import com.example.diplomtest.data.repository.TimerRepositoryImpl
import com.example.diplomtest.domain.useCases.GetTimerUseCase
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
        Log.d("StatsViewModel", "INIT")
        viewModelScope.launch(Dispatchers.IO){
            pointsList = getPointsListFromDB()
        }
    }

    suspend fun getAllDates(): List<Date> {
        return db.getAllDates()
    }

    suspend fun getDurationPlan(): List<Int>{
        return db.getSessions()
    }

    private suspend fun getPointsListFromDB(): List<Point>{
        val sessionsDB = db.getSessions()
        val list = ArrayList<Point>()
        Log.d("StatsViewModel", "getPointsListFromDB start")
        sessionsDB.forEachIndexed { index, session ->
            list.add(
                Point(
                    index.toFloat(),
                    session.toFloat()
                )
            )
        }

        list.forEach{
            Log.d("StatsViewModel", "${it.x} , ${it.y}")
        }


        return list
    }

    fun getPointsList(): List<Point>{
        return pointsList
    }
}