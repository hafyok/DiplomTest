package com.example.diplomtest.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diplomtest.View.Screen1
import com.example.diplomtest.View.Screen2

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "screen_1"){
        composable("screen_1"){
            Screen1()
        }
        //Тестовые изменения
        composable("screen_2"){
            Screen2()
        }

    }
}