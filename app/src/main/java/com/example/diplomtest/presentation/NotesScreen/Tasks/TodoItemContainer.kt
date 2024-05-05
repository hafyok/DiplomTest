package com.example.diplomtest.presentation.NotesScreen.Tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.diplomtest.domain.TodoData
import com.example.diplomtest.ui.constants.MediumDp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun TodoItemsContainer(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<TodoData>> = flowOf(listOf()),
    onItemClick: (TodoData) -> Unit = {},
    onItemDelete: (TodoData) -> Unit = {},
    overlappingElementsHeight: Dp = 0.dp
) {
    // 1. Flow Data Collection
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    // 2. LazyColumn Setup
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        // 3. Items Rendering
        items(todos, key = { it.id }) { item ->
            TodoItemUi(
                todoData = item,
                onItemClick = onItemClick,
                onItemDelete = onItemDelete
            )
        }
        // 4. Layout Adjustment
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TodoItemsContainerPreview() {
    TodoItemsContainer(
        todoItemsFlow = flowOf(
            listOf(
                TodoData(title = "Todo Item 1", isDone = true),
                TodoData(title = "Todo Item 2"),
                TodoData(title = "Todo Item 3"),
                TodoData(title = "Todo Item 4", isDone = true),
            )
        )
    )
}