package com.example.diplomtest.presentation.TimerScreen

import android.os.CountDownTimer
import android.util.Log

class CountDownTimerFun(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {
    var timerRemaining = 0L
    override fun onTick(millisUntilFinished: Long) {
        // Вызывается каждый раз, когда таймер совершает обратный отсчет
        val secondsRemaining = millisUntilFinished / 1000
        timerRemaining = secondsRemaining
        Log.d("MyCountDownTimer", "Seconds remaining: $timerRemaining")
    }

    override fun onFinish() {
        // Вызывается по завершении обратного отсчета
        Log.d("MyCountDownTimer", "CountDownTimer finished!")
    }
}

// Использование таймера
