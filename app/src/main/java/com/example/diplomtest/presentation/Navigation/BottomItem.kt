package com.example.diplomtest.presentation.Navigation

import com.example.diplomtest.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String){
    object Screen1: BottomItem("Timer screen", R.drawable.blur, "timer_screen")
    object Screen2: BottomItem("Stats screen", R.drawable.stats_icon_menu, "stats_screen")
    object Screen3: BottomItem("Notes screen", R.drawable.baseline_notes_24, "list_notes")
}