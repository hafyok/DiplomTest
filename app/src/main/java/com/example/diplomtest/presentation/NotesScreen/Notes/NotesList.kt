package com.example.diplomtest.presentation.NotesScreen.Notes

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diplomtest.R
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.data.database.Entity.NoteEntity
import com.example.diplomtest.data.database.Entity.getDay
import com.example.diplomtest.presentation.NotesScreen.Notes.Constants.orPlaceHolderList
import com.example.diplomtest.ui.theme.DiplomTestTheme
import com.example.diplomtest.ui.theme.noteBGBlue
import com.example.diplomtest.ui.theme.noteBGYellow


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesList(navController: NavController) {
    val application = Application()
    val viewModel = NotesViewModel(AppDatabase.getDatabase(application).notesDao())
    val openDialog = remember { mutableStateOf(false) }
    val deleteText = remember { mutableStateOf("") }
    val notesQuery = remember { mutableStateOf("") }
    val notesToDelete = remember { mutableStateOf(listOf<NoteEntity>()) }
    val notes = viewModel.notes.observeAsState()
    val context = LocalContext.current
    DiplomTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                topBar = { //TODO() исправить topBar
                    GenericAppBar(
                        title = stringResource(R.string.delete_all_notes),
                        onIconClick = {
                            if (notes.value?.isNotEmpty() == true) {
                                openDialog.value = true
                                deleteText.value = "Are you sure you want to delete all notes ?"
                                notesToDelete.value = notes.value ?: emptyList()
                            } else {
                                Toast.makeText(context, "No Notes found.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.ic_delete_all
                                ),
                                contentDescription = stringResource(id = R.string.delete_all_notes),
                                tint = Color.Black
                            )
                        },
                        iconState = remember { mutableStateOf(true) }

                    )
                },
                floatingActionButton = {
                    NotesFab(
                        contentDescription = stringResource(R.string.create_note),
                        action = { navController.navigate(Constants.NAVIGATION_NOTES_CREATE) },
                        icon = R.drawable.ic_create_note
                    )
                },

            ) {
                Column {
                    SearchBar(notesQuery)
                    NotesList(
                        notes = notes.value.orPlaceHolderList(),
                        query = notesQuery,
                        openDialog = openDialog,
                        deleteText = deleteText,
                        navController = navController,
                        notesToDelete = notesToDelete
                    )
                }

                DeleteDialog(
                    openDialog = openDialog,
                    text = deleteText,
                    notesToDelete = notesToDelete,
                    action = {
                        notesToDelete.value.forEach {
                            viewModel.deleteNotes(it)
                        }
                    })
            }

        }
    }
}

@Composable
fun SearchBar(query: MutableState<String>) {
    Column(Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 0.dp)) {
        TextField(
            value = query.value,
            placeholder = { Text("Search..") },
            maxLines = 1,
            onValueChange = { query.value = it },
            modifier = Modifier
                .background(Color.White)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = query.value.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { query.value = "" }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear),
                            contentDescription = stringResource(
                                R.string.clear_search
                            )
                        )
                    }
                }

            })

    }
}

@Composable
fun NotesList(
    notes: List<NoteEntity>,
    openDialog: MutableState<Boolean>,
    query: MutableState<String>,
    deleteText: MutableState<String>,
    navController: NavController,
    notesToDelete: MutableState<List<NoteEntity>>,
) {
    var previousHeader = ""
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.background(Color.Transparent)
    ) {
        val queriedNotes = if (query.value.isEmpty()) {
            notes
        } else {
            notes.filter { it.note.contains(query.value) || it.title.contains(query.value) }
        }
        itemsIndexed(queriedNotes) { index, note ->
            if (note.getDay() != previousHeader) {
                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = note.getDay(), color = Color.Black)
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )
                previousHeader = note.getDay()
            }


            NoteListItem(
                note,
                openDialog,
                deleteText = deleteText,
                navController,
                notesToDelete = notesToDelete,
                noteBackGround = if (index % 2 == 0) {
                    noteBGYellow
                } else noteBGBlue
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListItem(
    note: NoteEntity,
    openDialog: MutableState<Boolean>,
    deleteText: MutableState<String>,
    navController: NavController,
    noteBackGround: Color,
    notesToDelete: MutableState<List<NoteEntity>>
) {

    return Box(
        modifier = Modifier
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .background(noteBackGround)
                .fillMaxWidth()
                .height(120.dp)
                .combinedClickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                    onClick = {
                        if (note.id != 0) {
                            navController.navigate(Constants.noteEditNavigation(note.id ?: 0))
                        }
                    },
                    onLongClick = {
                        if (note.id != 0) {
                            openDialog.value = true
                            deleteText.value = "Are you sure you want to delete this note ?"
                            notesToDelete.value = mutableListOf(note)
                        }
                    }
                )

        ) {
            Row {

                Column {
                    Text(
                        text = note.title,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Text(
                        text = note.note,
                        color = Color.Black,
                        maxLines = 3,
                        modifier = Modifier.padding(12.dp)
                    )
                    Text(
                        text = note.dateUpdated,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun NotesFab(contentDescription: String, icon: Int, action: () -> Unit) {
    return FloatingActionButton(
        onClick = { action.invoke() },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            ImageVector.vectorResource(id = icon),
            contentDescription = contentDescription,
            tint = Color.Black
        )

    }
}

@Composable
fun DeleteDialog(
    openDialog: MutableState<Boolean>,
    text: MutableState<String>,
    action: () -> Unit,
    notesToDelete: MutableState<List<NoteEntity>>
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Delete Note")
            },
            text = {
                Column {
                    Text(text.value)
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            ),
                            onClick = {
                                action.invoke()
                                openDialog.value = false
                                notesToDelete.value = mutableListOf()
                            }
                        ) {
                            Text("Yes")
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            ),
                            onClick = {
                                openDialog.value = false
                                notesToDelete.value = mutableListOf()
                            }
                        ) {
                            Text("No")
                        }
                    }

                }
            }
        )
    }
}

