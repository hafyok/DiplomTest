package com.example.diplomtest.data.repository

import com.example.diplomtest.data.database.Dao.TodoDao
import com.example.diplomtest.data.database.Entity.TodoEntity
import com.example.diplomtest.domain.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val todoDao: TodoDao): TodoRepository {
    // 2. Data Retrieval
    val allTodos: Flow<List<TodoEntity>> = todoDao.getAllTodos()

    override suspend fun insert(todo: TodoEntity) {
        todoDao.insert(todo)
    }

    override suspend fun delete(todo: TodoEntity) {
        todoDao.delete(todo)
    }
}