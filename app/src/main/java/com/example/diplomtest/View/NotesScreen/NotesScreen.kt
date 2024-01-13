package com.example.diplomtest.View.NotesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.diplomtest.View.Navigation.BottomNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NotesScreen() {
    val navController = rememberNavController()
    val notes = listOf(
        "1", "2", "3"
    )
    //val notes by noteViewModel.getAllNotes().observeAsState()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Заголовок
                Text(
                    text = "My Notes",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                )

                // Список заметок
                LazyColumn {
                    items(notes ?: emptyList()) { note ->
                        //NoteItem(note = note)
                    }
                }


            }
        },

        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {
                        // Обработка нажатия на кнопку
                        // (Вы можете добавить здесь код для открытия экрана создания новой заметки)
                    },
                    modifier = Modifier
                        .padding(16.dp),
                    content = {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                    }
                )
            }
        },

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )

}

/*
@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Заголовок заметки
            Text(
                text = note.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Содержание заметки
            Text(
                text = note.content,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}
*/
