package com.example.todo.presentation.task_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = koinViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    
    LazyColumn {
        items(tasks){
            Text(text = it.title)
        }
    }
}