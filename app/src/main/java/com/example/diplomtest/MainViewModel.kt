package com.example.diplomtest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.data.AppDatabase
import com.example.diplomtest.data.TimerRepository
import com.example.diplomtest.data.TimerSessionEntity
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TimerRepository
    val allTasks: kotlinx.coroutines.flow.Flow<List<TimerSessionEntity>>

    init {
        val taskDao = AppDatabase.getDatabase(application).dao()
        repository = TimerRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun insert(timer: TimerSessionEntity) = viewModelScope.launch {
        repository.insert(timer)
    }

    /*fun update(task: TimerSessionEntity) = viewModelScope.launch {
        repository.update(task)
    }*/

    fun delete(timer: TimerSessionEntity) = viewModelScope.launch {
        repository.delete(timer)
    }
}
