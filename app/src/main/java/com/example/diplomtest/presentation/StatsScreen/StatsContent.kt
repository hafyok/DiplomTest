package com.example.diplomtest.presentation.StatsScreen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diplomtest.data.database.AppDatabase
import com.example.diplomtest.presentation.StatsScreen.StatsCards.BarChartCard
import com.example.diplomtest.presentation.StatsScreen.StatsCards.DonutChartCard
import com.example.diplomtest.presentation.StatsScreen.StatsCards.LineChartCard


//@Preview
@Composable
fun StatsContent(navController: NavController) {
    val application = Application()
    val viewModel = StatsViewModel(AppDatabase.getDatabase(application).timerSessionDao())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                LineChartCard(viewModel)
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                BarChartCard(viewModel)
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                DonutChartCard(viewModel)
            }
        }
    }
}
