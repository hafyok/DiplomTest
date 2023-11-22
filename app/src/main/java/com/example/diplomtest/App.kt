package com.example.diplomtest

import android.app.Application
import com.example.diplomtest.data.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
}