package com.example.diplomtest.domain

import androidx.lifecycle.LiveData
import java.util.Date

interface TimerRepository {

    suspend fun insertTimer(timer: TimerSessionData)

    suspend fun deleteTimer(timer: TimerSessionData)

    suspend fun deleteLastItem()

    suspend fun getTimerList(): LiveData<List<TimerSessionData>>

    suspend fun getAllDates(): List<Date>

    suspend fun getSessions(): List<Int>
}