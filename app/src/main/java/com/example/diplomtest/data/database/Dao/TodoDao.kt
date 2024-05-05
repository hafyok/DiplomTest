package com.example.diplomtest.data.database.Dao

import androidx.room.*
import com.example.diplomtest.data.database.Entity.TodoEntity
import kotlinx.coroutines.flow.Flow

// 1. Dao Annotation
@Dao
interface TodoDao {
    // 2. Items Flow
    @Query("SELECT * FROM TodoEntity")
    fun getAllTodos(): Flow<List<TodoEntity>>

    // 3. Insert Operation for Adding or Updating Items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoEntity)

    // 4. Delete an Item by TodoItem.id
    @Delete
    suspend fun delete(todo: TodoEntity)
}