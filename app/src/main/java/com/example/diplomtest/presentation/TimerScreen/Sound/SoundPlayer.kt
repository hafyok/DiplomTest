package com.example.diplomtest.presentation.TimerScreen.Sound

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diplomtest.R

@Composable
fun SoundPlayer(viewModel: SoundPlayerViewModel) {

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        /*Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = "",
            modifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .padding(32.dp)
                .background(Color.White)
        )*/
        Column() {
            Row() {

                IconButton(onClick = { viewModel.audioStart() }, modifier = Modifier.size(35.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.play_icon),
                        contentDescription = ""
                    )
                }

                IconButton(onClick = { viewModel.audioPause() }, modifier = Modifier.size(35.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pause),
                        contentDescription = ""
                    )
                }
            }
        }
        Column() {
            Column {
                Text(text = "LoFi",
                    Modifier
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.changeAudioLofi()})
                Text(text = "White Noises",
                    Modifier
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.changeAudioWhiteNoise()})
                Text(text = "Binaural beats",
                    Modifier
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.changeAudioLofi()})
            }
        }

    }
}