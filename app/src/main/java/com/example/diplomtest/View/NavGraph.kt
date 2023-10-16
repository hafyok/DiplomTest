package com.example.diplomtest.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diplomtest.View.TimerScreen.TimerScreenContent


@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "timer_screen"){
        composable("timer_screen"){
            TimerScreenContent()
        }
        composable("screen_2"){
            Screen2()
        }

    }
}