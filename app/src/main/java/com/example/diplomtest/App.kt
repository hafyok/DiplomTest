package com.example.diplomtest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.diplomtest.data.database.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
    override fun onCreate() {
        super.onCreate()
        // Запуск службы
        //startService(Intent(this, AppTrackingService::class.java))
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "running_channel",
                "Running Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}