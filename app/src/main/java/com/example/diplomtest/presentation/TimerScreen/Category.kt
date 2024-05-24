package com.example.diplomtest.presentation.TimerScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Category {
    val categoryList = listOf("Учеба", "Работа", "Отдых")
    private val mapAnimation = mapOf(
        "Учеба" to "lego.json",
        "Работа" to "work_guy.json",
        "Отдых" to "relax_guy.json"
    )

    var currentCategory by mutableStateOf(categoryList[0])
    var currentAnimation by mutableStateOf(mapAnimation[currentCategory].toString())

    fun updateCategory(newCategory: String) {
        currentCategory = newCategory
        currentAnimation = mapAnimation[currentCategory].toString()
        Log.d("Category", currentCategory)
        Log.d("Category", currentAnimation)
    }
}