package com.example.diplomtest

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log

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
}

object AppLifecycleObserver : Application.ActivityLifecycleCallbacks {

    private var isAppInForeground = false

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
