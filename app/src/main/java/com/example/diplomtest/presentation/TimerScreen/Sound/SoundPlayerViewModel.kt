package com.example.diplomtest.presentation.TimerScreen.Sound

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.diplomtest.R

class SoundPlayerViewModel(context: Context): ViewModel() {
    private val mp: MediaPlayer = MediaPlayer.create(context, R.raw.lofi)

    fun audioStart(){
        mp.start()
    }

    fun audioPause(){
        mp.pause()
    }

}