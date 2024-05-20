package com.example.diplomtest

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.example.diplomtest.presentation.MainScreen
import com.example.diplomtest.presentation.TimerScreen.Sound.SoundPlayerViewModel
import com.example.diplomtest.presentation.TimerScreen.Sound.SoundPlayerViewModelFactory
import com.example.diplomtest.presentation.TimerScreen.Timer.CountDownTimerViewModel
import com.example.diplomtest.ui.theme.DiplomTestTheme


class MainActivity : ComponentActivity() {
    private val countDownTimerViewModel: CountDownTimerViewModel by viewModels()
    private val soundPlayerViewModel: SoundPlayerViewModel by viewModels {
        SoundPlayerViewModelFactory(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        setContent {
            DiplomTestTheme {
                MainScreen(countDownTimerViewModel, soundPlayerViewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        stopForegroundService()
    }

    override fun onStop() {
        super.onStop()
        startForegroundService()
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    private fun stopForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        stopService(serviceIntent)
    }
}