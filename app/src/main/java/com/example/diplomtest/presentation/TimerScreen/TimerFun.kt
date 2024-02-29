package com.example.diplomtest.presentation.TimerScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplomtest.domain.TimerSessionData
import com.example.diplomtest.data.TimerSessionEntity


/*@Preview(showBackground = true, widthDp = 220)
@Composable
fun PreviewTimer(){
    Timer(
        totalTime = 150L * 1000L,
        handleColor = Color.Green,
        inactiveBarColor = Color.DarkGray,
        activeBarColor = Color(0xFF37B900),
        modifier = Modifier.size(200.dp)
    )
}*/


/*
@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )


        Button(
            onClick = {
                if(currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                    //insertTimer(newTimerData)

                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            */
/*colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )*//*


        ) {
            Text(
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }

        Button(
            onClick = {
                      viewModel.deleteLastItem()
            },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Text(
                text = "Удалить запись"
            )
        }
    }
    val newTimerData = TimerSessionData(done = false, categoty = Category.currentCategory)
    insertTimer(timerData = newTimerData) // <-нужно поправить код (конфликт с composable-функциями)
}
*/
@Preview(showBackground = true, widthDp = 220)
@Composable
fun PreviewTimer(){
    var timerValue by remember { mutableStateOf(10) }
    Timer(
        value = timerValue,
        onValueChange = { newValue ->
            timerValue = newValue
        },
        range = 1..100,
        label = "Timer Value"
    )
}

@Composable
fun Timer(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    label: String
) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val newValue = (value - 5).coerceIn(range)
                    onValueChange(newValue)
                }
            ) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = value.toString())
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val newValue = (value + 5).coerceIn(range)
                    onValueChange(newValue)
                }
            ) {
                Text("+")
            }
        }
    }
}

@Composable
fun TimerScreen() {
    var selectedTime by remember { mutableStateOf(10) }
    var isTimerRunning by remember { mutableStateOf(false) }
    val timer = remember {
        CountDownTimerFun(60000, 1000) // Таймер на 60 секунд с интервалом 1 секунда
    }
    var timeRemaining by remember { mutableStateOf(timer.timerRemaining * 1000L) }

    LaunchedEffect(timer.timerRemaining) {
        timeRemaining = timer.timerRemaining * 1000L
    }

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Timer(
            value = selectedTime,
            onValueChange = { newValue ->
                if (!isTimerRunning) {
                    selectedTime = newValue
                    timeRemaining = selectedTime * 1000L
                }
            },
            range = 1..100,
            label = "Select Time"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (!isTimerRunning) {
                        isTimerRunning = true
                        timer.start()
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Start Timer")
            }

            Button(
                onClick = {
                    isTimerRunning = false
                    //timer.cancel()
                    Log.d("MyLog", timer.timerRemaining.toString())
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Stop Timer")
            }
        }

        Text(
            text = "Time Remaining: ${(timeRemaining / 1000L)} seconds",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
@Preview
fun previewTimerScreen(){
    TimerScreen()
}
@Composable
fun insertTimer(timerData: TimerSessionData){
    val viewModel: TimerViewModel = viewModel()
    val timerModel = TimerSessionEntity(
        done = timerData.done,
        categoty = timerData.categoty
    )
    viewModel.insert(timerModel)
    Log.d("MeLog", "База дынных")
}