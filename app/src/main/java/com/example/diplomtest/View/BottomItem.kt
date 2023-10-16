package com.example.diplomtest.View

import com.example.diplomtest.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String){
    object Screen1: BottomItem("Timer screen", R.drawable.blur, "timer_screen")
    object Screen2: BottomItem("Screen 2", R.drawable.genos, "screen_2")
}