package com.example.diplomtest.data

import kotlinx.coroutines.flow.Flow

class TimerRepository(private val dao: Dao) {
    val allTasks: Flow<List<TimerSessionEntity>> = dao.getAllItems()

    suspend fun insert(timer: TimerSessionEntity){
        dao.insertItem(timer)
    }

    suspend fun delete(timer: TimerSessionEntity){
        dao.deleteItem(timer)
    }

    suspend fun deleteLastItem() {
        val lastItem = dao.getLastItem()
        lastItem?.let {
            dao.deleteItem(it)
        }
    }
}