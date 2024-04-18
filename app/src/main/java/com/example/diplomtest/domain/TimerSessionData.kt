package com.example.diplomtest.domain

import java.util.Date

data class TimerSessionData(
    val id: Int? = null,
    val date: Date?,
    val durationPlan: Int,
    val durationFact: Int?,
    val done: Boolean,
    val category: String
)
