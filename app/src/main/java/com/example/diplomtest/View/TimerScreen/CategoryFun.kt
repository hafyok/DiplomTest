package com.example.diplomtest.View.TimerScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diplomtest.MainViewModel
import com.example.diplomtest.data.TimerSessionData
import com.example.diplomtest.data.TimerSessionEntity

@Preview
@Composable
fun CategoryFun(){
    LazyRow(modifier = Modifier
        .padding(8.dp)
    ){
        items(Category.categoryList){category ->
            Column(modifier = Modifier.clickable {
                Category.currentCategory = category
                Log.d("TestCategory", Category.currentCategory)

            } ) {

            }
            Text(text = category)
            Spacer(modifier = Modifier.padding(10.dp))

        }
    }

}

