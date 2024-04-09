package com.example.diplomtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.diplomtest.presentation.MainScreen
import com.example.diplomtest.presentation.TimerScreen.CountDownTimerViewModel
import com.example.diplomtest.ui.theme.DiplomTestTheme


class MainActivity : ComponentActivity() {
    private val countDownTimerViewModel: CountDownTimerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiplomTestTheme {
                MainScreen(countDownTimerViewModel)
            }
        }
    }
}

