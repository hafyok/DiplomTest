package com.example.diplomtest.presentation.NotesScreen.Tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.diplomtest.R
import com.example.diplomtest.data.database.Entity.TodoEntity
import com.example.diplomtest.ui.constants.LargeDp
import com.example.diplomtest.ui.constants.MediumDp
import com.example.diplomtest.ui.constants.TodoItemActionButtonRippleRadius
import com.example.diplomtest.ui.constants.TodoItemHeight
import com.example.diplomtest.ui.constants.TodoItemIconSize
import com.example.diplomtest.ui.constants.TodoItemTitleTextStyle
import com.example.diplomtest.ui.theme.TodoItemBackgroundColor
import com.example.diplomtest.ui.theme.TodoItemIconColor
import com.example.diplomtest.ui.theme.TodoItemTextColor


@Composable
fun TodoItemUi(
    todoData: TodoEntity = TodoEntity(title = "Todo Item"),
    //  1. Lambda Function Parameters for Flexibility
    onItemClick: (TodoEntity) -> Unit = {},
    onItemDelete: (TodoEntity) -> Unit = {}
) {
    // 2. Adaptive Color Scheme
    val backgroundColor =
        if (todoData.isDone) TodoItemBackgroundColor.copy(alpha = 0.5f) else TodoItemBackgroundColor
    val textColor = if (todoData.isDone) TodoItemTextColor.copy(alpha = 0.5f) else TodoItemTextColor

    // 3. Text Decoration
    val textDecoration = if (todoData.isDone) TextDecoration.LineThrough else null

    // 4. Dynamic Icons
    val iconId =
        if (todoData.isDone) R.drawable.ic_selected_check_box else R.drawable.ic_empty_check_box
    val iconColorFilter =
        if (todoData.isDone) ColorFilter.tint(TodoItemIconColor.copy(alpha = 0.5f)) else ColorFilter.tint(
            TodoItemIconColor
        )
    val iconTintColor =
        if (todoData.isDone) TodoItemIconColor.copy(alpha = 0.5f) else TodoItemIconColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(TodoItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                // 5. Clickable Modifier with Ripple Effect:
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onItemClick(todoData) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(MediumDp)
                    .size(TodoItemIconSize),
                colorFilter = iconColorFilter
            )
            Text(
                text = todoData.title,
                modifier = Modifier.weight(1f),
                style = TodoItemTitleTextStyle.copy(color = textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = textDecoration
            )
            // 6. IconButton for Deletion
            IconButton(
                onClick = { onItemDelete(todoData) },
                modifier = Modifier.size(TodoItemActionButtonRippleRadius)
            ) {
                Icon(
                    modifier = Modifier.size(TodoItemIconSize),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = iconTintColor
                )
            }
        }
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TodoItemUi(todoData = TodoEntity(title = "Todo Item 1"))
        TodoItemUi(todoData = TodoEntity(title = "Todo Item 2", isDone = true))
        TodoItemUi(todoData = TodoEntity(title = "Todo Item 3"))
        TodoItemUi(todoData = TodoEntity(title = "Todo Item 4", isDone = true))
    }
}