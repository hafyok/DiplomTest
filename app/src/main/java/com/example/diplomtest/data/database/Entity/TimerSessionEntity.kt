package com.example.diplomtest.data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TimerSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val durationPlan: Int,
    val durationFact: Int?,
    val done: Boolean,
    val category: String,
    val date: Date = Date()
)
