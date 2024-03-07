package com.example.diplomtest.domain

import androidx.lifecycle.LiveData

interface TimerRepository {

    fun insertTimer(timer: TimerSessionData)

    suspend fun deleteTimer(timer: TimerSessionData)

    suspend fun deleteLastItem()

    suspend fun getTimerList(): LiveData<List<TimerSessionData>>
}