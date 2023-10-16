@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diplomtest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.diplomtest.ui.theme.DiplomTestTheme
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.diplomtest.View.BottomNavigation
import com.example.diplomtest.View.MainScreen
import com.example.diplomtest.View.TimerScreen.Timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiplomTestTheme {
                MainScreen()
            }
        }
    }
}


/*
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(){
    Scaffold(
        topBar = {
            AppBar(title = "My App")
        },
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String){
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable { 
                    
                }
            )
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, 
                contentDescription = "Search",
                modifier = Modifier.clickable { 
                    
                })
            Icon(imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.clickable {

                }
            )
        })
}
*/

