package com.example.diplomtest.data.repository

import androidx.lifecycle.LiveData
import com.example.diplomtest.data.database.TimerSessionDao
import com.example.diplomtest.data.database.TimerSessionEntity
import com.example.diplomtest.data.mapper.TimerMapper
import com.example.diplomtest.domain.TimerRepository
import com.example.diplomtest.domain.TimerSessionData
import kotlinx.coroutines.flow.Flow

class TimerRepositoryImpl(private val timerSessionDao: TimerSessionDao) : TimerRepository {
    val allTasks: Flow<List<TimerSessionEntity>> = timerSessionDao.getAllItems()
    private val mapper = TimerMapper()

    suspend fun insert(timer: TimerSessionEntity){
        timerSessionDao.insertItem(timer)
    }

    suspend fun delete(timer: TimerSessionEntity){
        timerSessionDao.deleteItem(timer)
    }

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