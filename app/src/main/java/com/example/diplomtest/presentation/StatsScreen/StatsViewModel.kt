package com.example.diplomtest.presentation.StatsScreen

import androidx.lifecycle.ViewModel
import com.example.diplomtest.data.database.TimerSessionDao
import com.example.diplomtest.data.repository.TimerRepositoryImpl
import com.example.diplomtest.domain.useCases.GetTimerUseCase
import java.util.Date

class StatsViewModel(private val db: TimerSessionDao): ViewModel() {
    val data = db.getAllItems()
    /*private val repository = TimerRepositoryImpl()

    private val getTimerUseCase = GetTimerUseCase()*/

    suspend fun getAllDates(): List<Date> {
        return db.getAllDates()
    }
}