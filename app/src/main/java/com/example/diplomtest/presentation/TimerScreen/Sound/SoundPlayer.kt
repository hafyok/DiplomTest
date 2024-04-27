package com.example.diplomtest.presentation.TimerScreen.Sound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diplomtest.R

@Composable
fun SoundPlayer(viewModel: SoundPlayerViewModel){

    Column(
        /*modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally*/
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = "",
            modifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .padding(32.dp)
                .background(Color.White)
        )

        Row() {

            IconButton(onClick = { viewModel.audioStart() }, modifier = Modifier.size(35.dp)) {
                Icon(painter = painterResource(id = R.drawable.play_icon), contentDescription = "")
            }

            IconButton(onClick = { viewModel.audioPause() }, modifier = Modifier.size(35.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_pause), contentDescription = "")
            }
        }
    }
}