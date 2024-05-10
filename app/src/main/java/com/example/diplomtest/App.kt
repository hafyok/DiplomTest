package com.example.diplomtest

import android.app.Application
import android.content.Intent
import com.example.diplomtest.data.database.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
    override fun onCreate() {
        super.onCreate()
        // Запуск службы
        startService(Intent(this, AppTrackingService::class.java))
    }
}