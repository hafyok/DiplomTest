package com.example.diplomtest.ViewModel

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.View.TimerScreen.TimeFormatExt.timeFormat
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CountDownTimerViewModel : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private val userInputHour = TimeUnit.HOURS.toMillis(1)
    private val userInputMinute = TimeUnit.MINUTES.toMillis(10)
    private val userInputSecond = TimeUnit.SECONDS.toMillis(30)

    val initialTotalTimeInMillis = userInputHour + userInputMinute + userInputSecond
    var timeLeft = mutableStateOf(initialTotalTimeInMillis)
    val countDownInterval = 1000L // 1 seconds is the lowest

    val timerText = mutableStateOf(timeLeft.value.timeFormat())

    val isPlaying = mutableStateOf(false)

    fun startCountDownTimer() = viewModelScope.launch {
        isPlaying.value = true
        countDownTimer = object : CountDownTimer(timeLeft.value, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeLeft.value = currentTimeLeft
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
        timeLeft.value = initialTotalTimeInMillis
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
