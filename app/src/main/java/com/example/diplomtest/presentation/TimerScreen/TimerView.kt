package com.example.diplomtest.presentation.TimerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplomtest.domain.TimerSessionData
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun TimerView(
    viewModel: CountDownTimerViewModel, timerViewModel: TimerViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.apply {

            Text(text = timerText.value, fontSize = 28.sp)

            LinearProgressIndicator(timeLeft.toFloat() / totalTimeInMillis.toFloat())

            Row {
                Button(onClick = {
                    decreaseTime()
                }) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (isPlaying.value) {
                        stopCountDownTimer()
                    } else {
                        startCountDownTimer(totalTimeInMillis)
                    }
                    coroutineScope.launch {
                        val timerData = TimerSessionData(
                            done = false,
                            category = Category.currentCategory,
                            durationPlan = (totalTimeInMillis / 1000 / 60).toInt(),
                            durationFact = null,
                            date = Date()
                        )
                        timerViewModel.insertTimer(timerData)
                        cancel()
                    }
                }) {
                    Text(text = if (isPlaying.value) "Stop CountDown" else "Start CountDown")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    increase()
                }) {
                    Text("+")
                }
            }

            Button(onClick = {
                resetCountDownTimer()
            }) {
                Text(text = "Reset Timer")
            }
        }
    }
}


/*
@Preview
@Composable
fun PreviewTestTimerScreen() {
    TimerView()
}*/
