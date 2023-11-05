package com.example.diplomtest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(nameSession: TimerSessionEntity)

    @Delete
    suspend fun deleteItem(nameSession: TimerSessionEntity)

    @Query("SELECT * FROM TimerSessionEntity")
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<TimerSessionEntity>>
}
