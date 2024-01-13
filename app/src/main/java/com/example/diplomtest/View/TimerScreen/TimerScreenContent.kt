package com.example.diplomtest.View.TimerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.diplomtest.ViewModel.MainViewModel
import com.example.diplomtest.View.Navigation.BottomNavigation

@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TimerScreenContent(
){
    val viewModel: MainViewModel = viewModel()
    var textState by rememberSaveable { mutableStateOf("Hello, World!") }
    val navController = rememberNavController()
    //val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    Scaffold(

        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(textState)
                Button(
                    onClick = {
                        textState = "Button clicked"
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Click me")
                }

                TestTimerScreen()

                Spacer(modifier = Modifier.padding(10.dp))

                CategoryFun()


            }
        },

        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { textState = "Fab clicked" },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        },

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
}
