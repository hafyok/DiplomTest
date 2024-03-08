@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diplomtest.presentation.NotesScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


//@Preview
/*
@Composable
fun NewNote(navController: NavController){
    Scaffold (
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                // Заголовок
                Text(
                    text = "Название",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        },

    )
}
*/


@Preview
@Composable
fun PreviewNewNote(){
    val navHostController = rememberNavController()
    NewNote(navController = navHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNote(navController: NavController) {
    // Состояния для заголовка и текста заметки
    var noteTitle by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }

    // Состояние для отображения модального окна
    var isDialogVisible by remember { mutableStateOf(true) }

    // Переключатель для закрытия модального окна
    val closeDialog: () -> Unit = { isDialogVisible = false }

    // Переключатель для открытия модального окна
    val openDialog: () -> Unit = { isDialogVisible = true }

    // Ваш код для обработки текста и заголовка заметки

    if (isDialogVisible) {
        // Показываем модальное окно
        Dialog(
            onDismissRequest = closeDialog,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Заголовок формы
                    Text(
                        text = "New Note",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center
                    )

                    // Поле для ввода заголовка заметки
                    TextField(
                        value = noteTitle,
                        onValueChange = { noteTitle = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    // Поле для ввода текста заметки
                    TextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        label = { Text("Note") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    // Кнопки "Сохранить" и "Отмена"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Кнопка "Сохранить"
                        Button(
                            onClick = {
                                // Обработка сохранения заметки
                                // ...

                                // Закрытие модального окна
                                closeDialog()
                                navController.popBackStack()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Save")
                        }

                        // Кнопка "Отмена"
                        Button(
                            onClick = {
                                // Закрытие модального окна
                                closeDialog()
                                navController.popBackStack()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cancel")
                        }
                    }
                }
            }
        )
    }
}