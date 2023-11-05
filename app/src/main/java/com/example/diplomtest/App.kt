package com.example.diplomtest

import android.app.Application
import com.example.diplomtest.data.MainDB

class App: Application() {
    val database by lazy {MainDB.createDataBase(this)}
}