package com.example.diplomtest.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.diplomtest.presentation.Navigation.BottomNavigation
import com.example.diplomtest.presentation.Navigation.NavGraph
import com.example.diplomtest.presentation.TimerScreen.Timer.CountDownTimerViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(countDownTimerViewModel: CountDownTimerViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        NavGraph(navHostController = navController, countDownTimerViewModel)
    }
}

