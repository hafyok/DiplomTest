package com.example.diplomtest.domain.useCases

import com.example.diplomtest.domain.TimerRepository
import com.example.diplomtest.domain.TimerSessionData

class InsertTimerUseCase(
    private val repository: TimerRepository,
) {
    operator fun invoke(timer: TimerSessionData) = repository.insertTimer(timer)
}