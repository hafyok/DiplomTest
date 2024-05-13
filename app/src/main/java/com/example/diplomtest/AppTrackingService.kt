package com.example.diplomtest

import android.app.Activity
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.diplomtest.AppLifecycleObserver.isAppInForeground

class AppTrackingService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("AppTrackingService", "Служба создана")
        // Подписываемся на состояние активности приложения
        AppLifecycleObserver.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отписываемся от состояния активности приложения при завершении службы
        AppLifecycleObserver.unregister(this)
    }

    fun onActivityPaused(activity: Activity) {
        // Приложение перешло в фоновый режим
        isAppInForeground = false
        // Отображаем уведомление
        //showNotification(activity)
        //Log.d("AppTrackingService", "OnActivityPaused")
    }

}

object AppLifecycleObserver : Application.ActivityLifecycleCallbacks {

    var isAppInForeground = false
    fun register(context: Context) {
        val appContext = context.applicationContext
        if (appContext is Application) {
            appContext.registerActivityLifecycleCallbacks(this)
        }
    }

    fun unregister(context: Context) {
        val appContext = context.applicationContext
        if (appContext is Application) {
            appContext.unregisterActivityLifecycleCallbacks(this)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        // Приложение перешло в фоновый режим
        isAppInForeground = false
        showNotification(activity)
        // Здесь можно отображать форму или экран с предупреждениями
        Log.d("AppTrackingService", "OnActivityPaused")
    }

    override fun onActivityResumed(activity: Activity) {
        // Приложение стало активным
        isAppInForeground = true
        Log.d("AppTrackingService", "OnActivityResumed")

    }

    // Другие неиспользуемые методы ActivityLifecycleCallbacks
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}
}

fun showNotification(context: Context) {
    val channelId = "default_channel_id"
    val channelName = "Default Channel"
    val notificationId = 1

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Создаем канал уведомлений для Android 8.0 и выше
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    // Создаем намерение для запуска активности, когда уведомление будет нажато
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    // Создаем уведомление
    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Внимание!")
        .setContentText("Приложение находится в фоновом режиме")
        .setSmallIcon(R.drawable.genos)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    // Отображаем уведомление
    notificationManager.notify(notificationId, notification)
}
