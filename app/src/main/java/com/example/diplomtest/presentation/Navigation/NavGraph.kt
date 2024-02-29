package com.example.diplomtest.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diplomtest.presentation.NotesScreen.NewNote
import com.example.diplomtest.presentation.NotesScreen.NotesScreen
import com.example.diplomtest.presentation.StatsScreen.StatsContent
import com.example.diplomtest.presentation.TimerScreen.TimerScreenContent


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