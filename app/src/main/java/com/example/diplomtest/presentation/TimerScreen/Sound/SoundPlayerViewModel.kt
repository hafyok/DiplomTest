package com.example.diplomtest.presentation.TimerScreen.Sound

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.diplomtest.R

//TODO() проверить утечку памяти (private val context: Context)
class SoundPlayerViewModel(private val context: Context): ViewModel() {
    private var mp: MediaPlayer = MediaPlayer.create(context, R.raw.lofi)
    var iconMusic by mutableIntStateOf(R.drawable.lofi_girl)
        private set

    fun audioStart(){
        mp.start()
    }

    fun audioPause(){
        mp.pause()
    }

    fun changeAudioWhiteNoise(){
        audioPause()
        mp = MediaPlayer.create(context, R.raw.white_noise)
        iconMusic = R.drawable.white_noise
        audioStart()
    }

    fun changeAudioLofi(){
        audioPause()
        mp = MediaPlayer.create(context, R.raw.lofi)
        iconMusic = R.drawable.lofi_girl
        audioStart()
    }

    fun changeAudioBeats(){
        audioPause()
        mp = MediaPlayer.create(context, R.raw.binaural_beats_40hz)
        iconMusic = R.drawable.binaural_beats
        audioStart()
    }

}