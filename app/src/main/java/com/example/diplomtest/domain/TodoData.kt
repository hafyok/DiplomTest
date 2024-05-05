package com.example.diplomtest.domain

import java.util.UUID

data class TodoData(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    var isDone: Boolean = false
)