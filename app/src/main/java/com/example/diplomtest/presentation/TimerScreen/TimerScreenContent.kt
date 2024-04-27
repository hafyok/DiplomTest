package com.example.diplomtest.presentation.TimerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diplomtest.presentation.Navigation.BottomNavigation
import com.example.diplomtest.presentation.TimerScreen.Sound.ModalContent
import com.example.diplomtest.presentation.TimerScreen.Sound.SoundPlayerViewModel
import com.example.diplomtest.presentation.TimerScreen.Timer.CountDownTimerViewModel
import com.example.diplomtest.presentation.TimerScreen.Timer.TimerView

//@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TimerScreenContent(
    navController: NavController,
    countDownTimerViewModel: CountDownTimerViewModel,
    soundPlayerViewModel: SoundPlayerViewModel
) {
    var isModalVisible by remember { mutableStateOf(false) }


    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    //SoundPlayer(viewModel = soundPlayerViewModel)
                    Button(
                        onClick = { isModalVisible = true },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Music")
                    }
                }
                if (isModalVisible) {
                    ModalContent(
                        onDismissRequest = { isModalVisible = false },
                        soundPlayerViewModel
                    )
                }

                TimerView(countDownTimerViewModel)

                Spacer(modifier = Modifier.padding(10.dp))

                CategoryFun()


            }
        },

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
}
