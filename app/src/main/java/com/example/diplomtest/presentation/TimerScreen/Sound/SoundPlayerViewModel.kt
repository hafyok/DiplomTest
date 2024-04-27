package com.example.diplomtest.presentation.TimerScreen.Sound

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.diplomtest.R

class SoundPlayerViewModel(private val context: Context): ViewModel() {
    private var mp: MediaPlayer = MediaPlayer.create(context, R.raw.lofi)

    fun audioStart(){
        mp.start()
    }

    fun audioPause(){
        mp.pause()
    }

    fun changeAudioWhiteNoise(){
        audioPause()
        mp = MediaPlayer.create(context, R.raw.white_noise)
        audioStart()
    }

    fun changeAudioLofi(){
        audioPause()
        mp = MediaPlayer.create(context, R.raw.lofi)
        audioStart()
    }

    //TODO() скачать биты

}