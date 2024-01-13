package com.example.diplomtest.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diplomtest.View.NotesScreen.NotesScreen
import com.example.diplomtest.View.StatsScreen.StatsContent
import com.example.diplomtest.View.TimerScreen.TimerScreenContent


@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "timer_screen"){
        composable("timer_screen"){
            TimerScreenContent()
        }
        composable("stats_screen"){
            StatsContent()
        }
        composable("notes_screen"){
            NotesScreen()
        }

    }
}