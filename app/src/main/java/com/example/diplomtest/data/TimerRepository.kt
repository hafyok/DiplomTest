package com.example.diplomtest.data

import kotlinx.coroutines.flow.Flow

class TimerRepository(private val timerSessionDao: TimerSessionDao) {
    val allTasks: Flow<List<TimerSessionEntity>> = timerSessionDao.getAllItems()

    suspend fun insert(timer: TimerSessionEntity){
        timerSessionDao.insertItem(timer)
    }

    suspend fun delete(timer: TimerSessionEntity){
        timerSessionDao.deleteItem(timer)
    }

    suspend fun deleteLastItem() {
        val lastItem = timerSessionDao.getLastItem()
        lastItem?.let {
            timerSessionDao.deleteItem(it)
        }
    }
}