package com.example.diplomtest.presentation.NotesScreen.Notes

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.diplomtest.R
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.ui.theme.DiplomTestTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateNoteScreen(
    navController: NavController,
) {
    val application = Application()
    val viewModel = NotesViewModel(AppDatabase.getDatabase(application).notesDao())

    val currentNote = remember { mutableStateOf("") }
    val currentTitle = remember { mutableStateOf("") }
    val currentPhotos = remember { mutableStateOf("") }
    val saveButtonState = remember { mutableStateOf(false) }


    /*val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) {
        if (it != null) {
            PhotoNotesApp.getUriPermission(it)
        }
        currentPhotos.value = it.toString()
    }
*/

    DiplomTestTheme {
        // A surface container using the 'background' color from the theme
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material.MaterialTheme.colors.primary
        ) {
            androidx.compose.material.Scaffold(topBar = {
                GenericAppBar(title = "Create Note", icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.blur),// TODO() исправить
                        contentDescription = stringResource(R.string.app_name), // TODO() исправить
                        tint = Color.Black,
                    )
                }, onIconClick = {
                    viewModel.createNote(
                        currentTitle.value,
                        currentNote.value,
                        currentPhotos.value
                    )
                    navController.popBackStack()
                }, iconState = saveButtonState
                )
            },


                content = {
                    Column(
                        Modifier
                            .padding(12.dp)
                            .fillMaxSize()
                    ) {
                        if (currentPhotos.value.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = Uri.parse(currentPhotos.value)).build()
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxHeight(0.3f)
                                    .padding(6.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        TextField(value = currentTitle.value,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = Color.Black,
                                focusedLabelColor = Color.Black,
                            ),
                            onValueChange = { value ->
                                currentTitle.value = value
                                saveButtonState.value =
                                    currentTitle.value != "" && currentNote.value != ""
                            },
                            label = { Text(text = "Title") })
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextField(value = currentNote.value,
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = Color.Black,
                                focusedLabelColor = Color.Black,
                            ),
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(),
                            onValueChange = { value ->
                                currentNote.value = value
                                saveButtonState.value =
                                    currentTitle.value != "" && currentNote.value != ""
                            },
                            label = { Text(text = "Body") })
                    }
                }
            )
        }
    }
}

