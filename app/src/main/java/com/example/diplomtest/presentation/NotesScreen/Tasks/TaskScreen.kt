package com.example.diplomtest.presentation.NotesScreen.Tasks

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.data.repository.TodoRepositoryImpl
import com.example.diplomtest.ui.constants.OverlappingHeight
import kotlinx.coroutines.Dispatchers

@Preview(showSystemUi = true)
@Composable
fun TaskScreen() {
    val application = Application()
    // 2. Manual MainViewModel Creation
    val todoViewModel = TodoViewModel(
        TodoRepositoryImpl(AppDatabase.getDatabase(application).todoDao()),
        ioDispatcher = Dispatchers.IO
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TodoItemsContainer(
            todoItemsFlow = todoViewModel.todos,
            // 3. Method Reference Expression
            onItemClick = todoViewModel::toggleTodo,
            onItemDelete = todoViewModel::removeTodo,
            overlappingElementsHeight = OverlappingHeight
        )
        TodoInputBar(
            modifier = Modifier.align(Alignment.BottomStart),
            onAddButtonClick = todoViewModel::addTodo
        )
    }
}