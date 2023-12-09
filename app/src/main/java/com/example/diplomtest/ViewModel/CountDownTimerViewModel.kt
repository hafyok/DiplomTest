package com.example.diplomtest.ViewModel

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.View.TimerScreen.TimeFormatExt.timeFormat
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

    val timerText = mutableStateOf(timeLeft.timeFormat())

    val isPlaying = mutableStateOf(false)

    fun startCountDownTimer() = viewModelScope.launch {
        isPlaying.value = true
        countDownTimer = object : CountDownTimer(timeLeft, countDownInterval) {
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
}

/*
class CountDownTimerViewModel : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    */
/*var userInputHour = mutableStateOf( TimeUnit.HOURS.toMillis(1))
    var userInputMinute = mutableStateOf( TimeUnit.MINUTES.toMillis(10))
    var userInputSecond = mutableStateOf( TimeUnit.SECONDS.toMillis(30))*//*


    var userInputHour = mutableLongStateOf( TimeUnit.HOURS.toMillis(1L))
    var userInputMinute = mutableLongStateOf( TimeUnit.MINUTES.toMillis(10L))
    var userInputSecond = mutableLongStateOf( TimeUnit.SECONDS.toMillis(30L))

    var initialTotalTimeInMillis = userInputHour.longValue + userInputMinute.value + userInputSecond.value
    var timeLeft = mutableLongStateOf(initialTotalTimeInMillis)
    val countDownInterval = 1000L // 1 seconds is the lowest

    var timerText = mutableStateOf(timeLeft.value.timeFormat())

    val isPlaying = mutableStateOf(false)

    fun startCountDownTimer() = viewModelScope.launch {
        isPlaying.value = true
        countDownTimer = object : CountDownTimer(timeLeft.value, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                timerText = mutableStateOf( currentTimeLeft.timeFormat())
                timeLeft = mutableLongStateOf( currentTimeLeft)

                Log.d("TimerLog", timerText.value )
                Log.d("TimerLog", timeLeft.value.toString() + "Left")
            }

            override fun onFinish() {
                timerText = mutableStateOf( initialTotalTimeInMillis.timeFormat())
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
        timerText = mutableStateOf( initialTotalTimeInMillis.timeFormat())
        timeLeft = mutableLongStateOf(initialTotalTimeInMillis)
    }
}*/
