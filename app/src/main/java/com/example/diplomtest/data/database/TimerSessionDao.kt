package com.example.diplomtest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TimerSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(nameSession: TimerSessionEntity)

    @Delete
    suspend fun deleteItem(nameSession: TimerSessionEntity)

    @Query("SELECT * FROM TimerSessionEntity ORDER BY id DESC LIMIT 1")
    suspend fun getLastItem(): TimerSessionEntity?

    @Query("SELECT * FROM TimerSessionEntity")
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<TimerSessionEntity>>
}
