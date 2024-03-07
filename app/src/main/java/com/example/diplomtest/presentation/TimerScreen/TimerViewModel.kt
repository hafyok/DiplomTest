package com.example.diplomtest.presentation.TimerScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.diplomtest.data.repository.TimerRepositoryImpl
import com.example.diplomtest.domain.TimerSessionData
import com.example.diplomtest.domain.useCases.InsertTimerUseCase

class TimerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =  TimerRepositoryImpl(application)

    //TODO() - добавить юз-кейсы
    private val insertTimerUseCase = InsertTimerUseCase(repository)

    /*init {
        val taskDao = AppDatabase.getDatabase(application).timerSessionDao()
        repository = TimerRepositoryImpl(taskDao)
        allTasks = repository.allTasks
    }*/

    /*fun insertTimer(timer: TimerSessionData) = viewModelScope.launch {
        repository.insertTimer(timer)
    }*/

    suspend fun insertTimer(timer: TimerSessionData) = insertTimerUseCase(timer)
    /*fun update(task: TimerSessionEntity) = viewModelScope.launch {
        repository.update(task)
    }*/

    /*fun delete(timer: TimerSessionEntity) = viewModelScope.launch {
        repository.delete(timer)
    }

    fun deleteLastItem() = viewModelScope.launch {
        repository.deleteLastItem()
    }*/
}
