package com.example.diplomtest.data.database

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

//А это вообще нужно?
@TypeConverter
fun fromDate(date: Date?): Long? {
    return date?.time
}

//А это вообще нужно?
@TypeConverter
fun toDate(timestamp: Long?): Date? {
    return timestamp?.let { Date(it) }
}