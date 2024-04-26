package com.example.diplomtest.presentation.TimerScreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diplomtest.presentation.Navigation.BottomNavigation
import com.example.diplomtest.presentation.TimerScreen.Sound.SoundPlayer
import com.example.diplomtest.presentation.TimerScreen.Timer.CountDownTimerViewModel
import com.example.diplomtest.presentation.TimerScreen.Timer.TimerView

//@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TimerScreenContent(navController: NavController, countDownTimerViewModel: CountDownTimerViewModel){
    var textState by rememberSaveable { mutableStateOf("Hello, World!") }
    val context = LocalContext.current

    Scaffold(

        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SoundPlayer(context = context)
                Text(textState)
                Button(
                    onClick = {
                        textState = "Button clicked"
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Click me")
                }

                TimerView(countDownTimerViewModel)

                Spacer(modifier = Modifier.padding(10.dp))

                CategoryFun()


            }
        },

        /*floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { textState = "Fab clicked" },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        },*/

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
}

/*
@Preview
@Composable
fun PreviewTimerScreenContent(){
    val navController = rememberNavController()
    TimerScreenContent(navController)
}*/