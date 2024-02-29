package com.example.diplomtest

import android.app.Application
import com.example.diplomtest.data.database.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
}