package com.example.diplomtest.presentation.TimerScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.data.repository.TimerRepositoryImpl
import com.example.diplomtest.data.database.TimerSessionEntity
import kotlinx.coroutines.launch

class TimerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TimerRepositoryImpl
    val allTasks: kotlinx.coroutines.flow.Flow<List<TimerSessionEntity>>
    //TODO() - добавить юз-кейсы

    init {
        val taskDao = AppDatabase.getDatabase(application).timerSessionDao()
        repository = TimerRepositoryImpl(taskDao)
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

    fun deleteLastItem() = viewModelScope.launch {
        repository.deleteLastItem()
    }
}
