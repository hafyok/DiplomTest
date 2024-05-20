package com.example.diplomtest.domain

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NoteData(
    val id: Int? = null,
    val note: String,
    val title: String,
    val dateUpdated: String = getDateCreated(),
    val imageUri: String? = null //TODO() это не нужно
)

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}


