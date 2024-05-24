package com.example.diplomtest.presentation.TimerScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.diplomtest.R
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
    soundPlayerViewModel: SoundPlayerViewModel,
    animationViewModel: AnimationViewModel = viewModel()
) {
    var isModalVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset(Category.currentAnimation)
        //spec = LottieCompositionSpec.Asset("relax_guy.json")

    )

    val isPlaying by animationViewModel.isPlaying.collectAsState()

    Scaffold(content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = {
                    sendEmail(context)
                }) {
                    Icon(imageVector = Icons.Rounded.Feedback, contentDescription = "Feedback")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    isPlaying = isPlaying,
                    iterations = LottieConstants.IterateForever,
                    reverseOnRepeat = true,
                    modifier = Modifier
                        .size(200.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    FilledIconButton(
                        onClick = { isModalVisible = true },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(imageVector = Icons.Filled.Headphones, contentDescription = "Music")
                    }
                }
                if (isModalVisible) {
                    ModalContent(
                        onDismissRequest = { isModalVisible = false }, soundPlayerViewModel
                    )
                }

                TimerView(countDownTimerViewModel, animationViewModel = animationViewModel)

                Spacer(modifier = Modifier.padding(10.dp))

                CategoryFun()
            }
        }
    },
        bottomBar = {
            BottomNavigation(navController = navController)
        })
}

@SuppressLint("QueryPermissionsNeeded")
fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.email_address)))
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.title_email))
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.text_email))
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
