package com.example.diplomtest.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.data.mapper.TimerMapper
import com.example.diplomtest.domain.TimerRepository
import com.example.diplomtest.domain.TimerSessionData

class TimerRepositoryImpl(
    //private val timerSessionDao: TimerSessionDao
    private val application: Application
) : TimerRepository {

    private val timerSessionDao = AppDatabase.getDatabase(application).timerSessionDao()

    private val mapper = TimerMapper()

    /*suspend fun insertTimer(timer: TimerSessionEntity) {
        timerSessionDao.insertItem(timer)
    }

    suspend fun delete(timer: TimerSessionEntity) {
        timerSessionDao.deleteItem(timer)
    }*/

    /*suspend fun deleteLastItem() {
        val lastItem = timerSessionDao.getLastItem()
        lastItem?.let {
            timerSessionDao.deleteItem(it)
        }
    }*/

    override suspend fun insertTimer(timer: TimerSessionData) {
        timerSessionDao.insertItem(mapper.mapModelToEntity(timer))
    }

    override suspend fun deleteTimer(timer: TimerSessionData) {
        timerSessionDao.deleteItem(mapper.mapModelToEntity(timer))
    }

    override suspend fun deleteLastItem() {
        TODO("Not yet implemented")
    }

    override suspend fun getTimerList(): LiveData<List<TimerSessionData>> {
        TODO("Not yet implemented")
    }
}