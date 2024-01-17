package com.example.diplomtest.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diplomtest.View.NotesScreen.NewNote
import com.example.diplomtest.View.NotesScreen.NotesScreen
import com.example.diplomtest.View.StatsScreen.StatsContent
import com.example.diplomtest.View.TimerScreen.TimerScreenContent


@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "timer_screen"){
        composable("timer_screen"){
            TimerScreenContent(navHostController)
        }
        composable("stats_screen"){
            StatsContent(navHostController)
        }
        composable("notes_screen"){
            NotesScreen(navHostController)
        }
        composable("new_note_screen"){
            NewNote(navHostController)
        }

    }
}