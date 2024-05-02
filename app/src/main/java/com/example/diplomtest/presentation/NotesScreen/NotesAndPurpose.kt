@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.diplomtest.presentation.NotesScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NotesPurposeScreen(navController: NavController) {
    val tabItems = listOf(
        TabItem("Notes", Icons.AutoMirrored.Outlined.Notes, Icons.AutoMirrored.Filled.Notes) {
            NotesList(navController = navController)
        },
        TabItem("Tasks", Icons.Outlined.TaskAlt, Icons.Filled.TaskAlt) {
            DummyContent2(
                index = 1
            )
        }
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }

    }

    Column(modifier = Modifier.fillMaxSize()) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            }, contentDescription = item.title
                        )
                    },

                    )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                tabItems[index].content() // передача параметра index
            }
        }

    }

}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val content: @Composable () -> Unit
)

@Composable
fun DummyContent2(index: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Content for Tab $index", fontSize = 18.sp)
    }
}
