package com.example.diplomtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity
data class TimerSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    //val date: Date,
    //val duration: Long,
    val done: Boolean,
    val categoty: String

)

@TypeConverter
fun fromDate(date: Date?): Long? {
    return date?.time
}

@TypeConverter
fun toDate(timestamp: Long?): Date? {
    return timestamp?.let { Date(it) }
}