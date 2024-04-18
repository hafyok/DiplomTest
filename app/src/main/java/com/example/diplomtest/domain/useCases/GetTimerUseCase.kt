package com.example.diplomtest.domain.useCases

import com.example.diplomtest.domain.TimerRepository

class GetTimerUseCase(
    private val repository: TimerRepository
) {
    suspend operator fun invoke() = repository.getAllDates()
}