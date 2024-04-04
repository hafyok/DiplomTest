package com.example.diplomtest.presentation.TimerScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplomtest.domain.TimerSessionData
import com.example.diplomtest.presentation.TimerScreen.TimeFormatExt.timeFormat
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun TimerView(
    viewModel: CountDownTimerViewModel = viewModel(),
    timerViewModel: TimerViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.apply {

            Text(text = timerText.value, fontSize = 28.sp)

            LinearProgressIndicator(timeLeft.toFloat() / totalTimeInMillis.toFloat())

            Row {
                Button(
                    onClick = {
                        timeLeft -= 300000
                        totalTimeInMillis -= 300000
                        timerText.value = timeLeft.timeFormat()
                        Log.d("Timer", totalTimeInMillis.toString())
                    }
                ) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (isPlaying.value) {
                        stopCountDownTimer()
                    } else {
                        //totalTimeInMillis = timeLeft
                        startCountDownTimer(totalTimeInMillis)
                    }
                    coroutineScope.launch {
                        val timerData = TimerSessionData(12, false, "TeeeSt")
                        timerViewModel.insertTimer(timerData)
                        cancel()
                    }
                }) {
                    Text(text = if (isPlaying.value) "Stop CountDown" else "Start CountDown")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        timeLeft += 300000
                        totalTimeInMillis += 300000
                        timerText.value = timeLeft.timeFormat()
                        Log.d("Timer", totalTimeInMillis.toString())
                    }
                ) {
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


@Preview
@Composable
fun PreviewTestTimerScreen(){
    TimerView()
}