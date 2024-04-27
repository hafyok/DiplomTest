package com.example.diplomtest.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diplomtest.Constants
import com.example.diplomtest.presentation.NotesScreen.CreateNoteScreen
import com.example.diplomtest.presentation.NotesScreen.NoteEditScreen
import com.example.diplomtest.presentation.NotesScreen.NotesList
import com.example.diplomtest.presentation.StatsScreen.StatsContent
import com.example.diplomtest.presentation.TimerScreen.Sound.SoundPlayerViewModel
import com.example.diplomtest.presentation.TimerScreen.Timer.CountDownTimerViewModel
import com.example.diplomtest.presentation.TimerScreen.TimerScreenContent


@Composable
fun NavGraph(
    navHostController: NavHostController,
    countDownTimerViewModel: CountDownTimerViewModel,
    soundPlayerViewModel: SoundPlayerViewModel
) {
    NavHost(navController = navHostController, startDestination = "timer_screen") {
        composable("timer_screen") {
            TimerScreenContent(navHostController, countDownTimerViewModel, soundPlayerViewModel)
        }
        composable("stats_screen") {
            StatsContent(navHostController)
        }
        composable("notesCreated") {
            CreateNoteScreen(navHostController)
        }
        composable("list_notes"){
            NotesList(navController = navHostController)
        }
        // Notes Edit page
        composable(
            Constants.NAVIGATION_NOTE_EDIT,
            arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                ?.let { NoteEditScreen(noteId = it, navHostController) }
        }

    }
}