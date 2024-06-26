package com.example.diplomtest.data.database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diplomtest.data.database.Entity.TimerSessionEntity
import java.util.Date


@Dao
interface TimerSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(nameSession: TimerSessionEntity)

    @Delete
    suspend fun deleteItem(nameSession: TimerSessionEntity)

    @Query("SELECT * FROM TimerSessionEntity ORDER BY id DESC LIMIT 1")
    suspend fun getLastItem(): TimerSessionEntity?

    @Query("SELECT * FROM TimerSessionEntity")
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<TimerSessionEntity>>

    @Query("SELECT date FROM TimerSessionEntity")
    suspend fun getAllDates(): List<Date>

    @Query("SELECT durationPlan FROM TimerSessionEntity")
    suspend fun getSessions(): List<Int>
}
