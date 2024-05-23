package com.example.diplomtest.presentation.TimerScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnimationViewModel : ViewModel() {
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> get() = _isPlaying

    fun startAnimation() {
        _isPlaying.value = true
    }

    fun stopAnimation() {
        _isPlaying.value = false
    }
}