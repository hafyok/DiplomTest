package com.example.diplomtest.presentation.NotesScreen.Tasks

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.diplomtest.domain.TodoData
import com.example.diplomtest.ui.constants.OverlappingHeight
import kotlinx.coroutines.flow.flowOf

@Preview(showSystemUi = true)
@Composable
fun TaskScreen(){
    val mContext = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TodoItemsContainer(
            // 1. Mock Data for Todo Items
            todoItemsFlow = flowOf(
                listOf(
                    TodoData(title = "Todo Item 1"),
                    TodoData(title = "Todo Item 2", isDone = true),
                    TodoData(title = "Todo Item 3"),
                    TodoData(title = "Todo Item 4", isDone = true),
                    TodoData(title = "Todo Item 5"),
                    TodoData(title = "Todo Item 6"),
                    TodoData(title = "Todo Item 11", isDone = true),
                )
            ),
            onItemClick = { Toast.makeText(mContext, "CLICK", Toast.LENGTH_LONG).show()},
            onItemDelete = {Toast.makeText(mContext, "DELETE", Toast.LENGTH_LONG).show()},
            // 2. Space Adjustment for Overlapping UI Elements
            overlappingElementsHeight = OverlappingHeight
        )
        TodoInputBar(
            modifier = Modifier.align(Alignment.BottomStart),
            onAddButtonClick = {Toast.makeText(mContext, "ADD", Toast.LENGTH_LONG).show()}
        )
    }
}