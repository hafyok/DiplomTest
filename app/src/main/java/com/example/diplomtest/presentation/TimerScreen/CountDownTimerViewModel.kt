package com.example.diplomtest.presentation.TimerScreen

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.presentation.TimerScreen.TimeFormatExt.timeFormat
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CountDownTimerViewModel : ViewModel() {
    //var uiState by mutableStateOf(CountUiState())

    private var countDownTimer: CountDownTimer? = null

    var userInputHour = TimeUnit.HOURS.toMillis(0)
    var userInputMinute = TimeUnit.MINUTES.toMillis(45)
    var userInputSecond = TimeUnit.SECONDS.toMillis(0)


    var initialTotalTimeInMillis = userInputHour + userInputMinute + userInputSecond
    //var initialTotalTimeInMillis by mutableStateOf(userInputHour + userInputMinute + userInputSecond)
    //var timeLeft = mutableStateOf(initialTotalTimeInMillis)
    var timeLeft by mutableStateOf(initialTotalTimeInMillis)
    val countDownInterval = 1000L // 1 seconds is the lowest

    var totalTimeInMillis = initialTotalTimeInMillis

    val timerText = mutableStateOf(timeLeft.timeFormat())

    val isPlaying = mutableStateOf(false)

    fun startCountDownTimer(timeInMillis: Long) = viewModelScope.launch {
        isPlaying.value = true
        countDownTimer = object : CountDownTimer(timeInMillis, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeLeft = currentTimeLeft
            }

            override fun onFinish() {
                timerText.value = initialTotalTimeInMillis.timeFormat()
                isPlaying.value = false
            }
        }.start()
    }

    fun stopCountDownTimer() = viewModelScope.launch {
        isPlaying.value = false
        countDownTimer?.cancel()
    }

    fun resetCountDownTimer() = viewModelScope.launch {
        isPlaying.value = false
        countDownTimer?.cancel()
        timerText.value = initialTotalTimeInMillis.timeFormat()
        timeLeft = initialTotalTimeInMillis
    }

    fun decreaseTime(){
        timeLeft -= 300000
        totalTimeInMillis -= 300000
        timerText.value = timeLeft.timeFormat()
    }

    fun increase(){
        timeLeft += 300000
        totalTimeInMillis += 300000
        timerText.value = timeLeft.timeFormat()
    }
}


