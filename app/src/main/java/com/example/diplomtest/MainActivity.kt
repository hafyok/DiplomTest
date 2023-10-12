@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diplomtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import android.widget.NumberPicker
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiplomTestTheme {
                //MyApp()
                //MyScreenContent()
                MainScreen()
            }
        }


    }
}


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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MyScreenContent(){
    var textState by rememberSaveable { mutableStateOf("Hello, World!") }
    val navController = rememberNavController()

    Scaffold(
        /*topBar = {
            TopAppBar(title = { Text(text = "Jetpack Compose") },
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
        },
*/
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(textState)
                Button(
                    onClick = {
                        textState = "Button clicked"
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Click me")
                }
                //SliderMinimalExample()
                Timer(
                    totalTime = 150L * 1000L,
                    handleColor = Color.Green,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFF37B900),
                    modifier = Modifier.size(200.dp)
                )

            }
        },

        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { textState = "Fab clicked" },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        },

        bottomBar = {
            /*BottomAppBar {
                BottomBar()
            }*/
            BottomNavigation(navController = navController)
        }
    )
}