package com.example.diplomtest

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(){
    Row(){
        Spacer(Modifier.weight(0.3f, true))
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Home, contentDescription = "Menu")
        }
        Spacer(Modifier.weight(0.3f, true))
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Dashboard, contentDescription = "Favorite")
        }
        Spacer(Modifier.weight(0.3f, true))
//        Spacer(Modifier.weight(1f, true))
//        IconButton(onClick = {}) {
//            Icon(Icons.Filled.Share, contentDescription = "Share")
//        }
    }

}