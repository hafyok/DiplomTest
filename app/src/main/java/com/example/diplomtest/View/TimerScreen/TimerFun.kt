package com.example.diplomtest.View.TimerScreen

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplomtest.ViewModel.MainViewModel
import com.example.diplomtest.data.TimerSessionData
import com.example.diplomtest.data.TimerSessionEntity
//import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin


//@Preview(showBackground = true)
@Composable
fun SliderMinimalExample() {
    var timer: CountDownTimer? = null

    var sliderPosition by remember { mutableStateOf(40f) }
    var textTimer by remember { mutableStateOf(40) }

    //var textState by rememberSaveable { mutableStateOf("Hello, World!") }
    var timeRemaining by remember { mutableStateOf(40_000L) } // Начальное время в миллисекундах (в данном случае, 40 секунд)
    var isTimerRunning by remember { mutableStateOf(false) }

    fun startTimer() {
        timer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                textTimer = timeRemaining.toInt() / 1000

                Log.d("MyLog", timeRemaining.toString())
            }

            override fun onFinish() {
                isTimerRunning = false
            }
        }
        timer?.start()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text(text = sliderPosition.toInt().toString())
        //textTimer = sliderPosition.toInt()
        //timeRemaining = sliderPosition.toInt() * 1000L
        Text(text = textTimer.toString())
        Spacer(modifier = Modifier.padding(10.dp))
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            steps = 21,
            valueRange = 10f..120f,
        )
        //Text("Time Remaining: ${(textTimer)} seconds")

        Button(
            onClick = {
                Log.d("MyLog", isTimerRunning.toString())
                Log.d("MyLog", timeRemaining.toString())


                if (isTimerRunning) {
                    timer?.cancel()
                    isTimerRunning = false
                } else {
                    startTimer()
                    isTimerRunning = true
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(if (isTimerRunning) "Stop Timer" else "Start Timer")
        }

    }

}

@Preview(showBackground = true, widthDp = 220,)
@Composable
fun PreviewTimer(){
    Timer(
        totalTime = 150L * 1000L,
        handleColor = Color.Green,
        inactiveBarColor = Color.DarkGray,
        activeBarColor = Color(0xFF37B900),
        modifier = Modifier.size(200.dp)
    )
}

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
    val viewModel: MainViewModel = viewModel()
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
            /*colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )*/
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

@Composable
fun insertTimer(timerData: TimerSessionData){
    val viewModel: MainViewModel = viewModel()
    val timerModel = TimerSessionEntity(
        done = timerData.done,
        categoty = timerData.categoty
    )
    viewModel.insert(timerModel)
    Log.d("MeLog", "База дынных")
}