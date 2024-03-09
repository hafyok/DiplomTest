package com.example.diplomtest.domain

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NoteData(
    val id: Int? = null,
    val note: String,
    val title: String,
    val dateUpdated: String = getDateCreated(),
    val imageUri: String? = null
)

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun NoteData.getDay(): String {
    if (this.dateUpdated.isEmpty()) return ""
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this.dateUpdated, formatter).toLocalDate()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
