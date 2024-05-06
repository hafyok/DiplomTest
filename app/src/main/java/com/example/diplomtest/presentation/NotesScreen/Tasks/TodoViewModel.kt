package com.example.diplomtest.presentation.NotesScreen.Tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.data.database.Entity.TodoEntity
import com.example.diplomtest.data.repository.TodoRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TodoViewModel (
    // 1. ViewModel Initialization
    private val repository: TodoRepositoryImpl,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val todos = repository.allTodos

    fun addTodo(todo: String) =
        viewModelScope.launch(ioDispatcher) { repository.insert(TodoEntity(title = todo)) }

    fun toggleTodo(todoItem: TodoEntity) =
        viewModelScope.launch(ioDispatcher) { repository.insert(todoItem.copy(isDone = !todoItem.isDone)) }

    fun removeTodo(todoItem: TodoEntity) =
        viewModelScope.launch(ioDispatcher) { repository.delete(todoItem) }
}