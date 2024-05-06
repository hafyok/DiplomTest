package com.example.diplomtest.domain

import com.example.diplomtest.data.database.Entity.TodoEntity

interface TodoRepository {
    suspend fun insert(todo: TodoEntity)

    suspend fun delete(todo: TodoEntity)
}