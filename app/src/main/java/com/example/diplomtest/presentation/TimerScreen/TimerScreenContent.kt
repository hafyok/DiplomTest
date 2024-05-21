package com.example.diplomtest.presentation.TimerScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    Scaffold(content = { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = {
                sendEmail(context, "hafyok5777@gmail.com")
            }) {
                Icon(imageVector = Icons.Rounded.Feedback, contentDescription = null)
            }
        }
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
                    onDismissRequest = { isModalVisible = false }, soundPlayerViewModel
                )
            }

            TimerView(countDownTimerViewModel)

            Spacer(modifier = Modifier.padding(10.dp))

            CategoryFun()


        }
    },

        bottomBar = {
            BottomNavigation(navController = navController)
        })
}

fun sendEmail(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        // Добавьте другие данные, например тему или текст сообщения, если нужно:
        putExtra(Intent.EXTRA_SUBJECT, "Тема сообщения")
        putExtra(Intent.EXTRA_TEXT, "Текст сообщения")
    }

    // Убедитесь, что есть приложение, которое может обработать этот intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
