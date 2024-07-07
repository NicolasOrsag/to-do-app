package com.example.todo.presentation.task_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.data.local.model.Task
import com.example.todo.presentation.components.DeleteConfirmationDialog
import com.example.todo.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskListViewModel = koinViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(tasks) { task ->
                TaskItem(task = task,
                    onDeleteClick = { viewModel.deleteTask(task) },
                    onToggleCompleted = { viewModel.toggleCompleted(task) },
                    onItemClick = { navController.navigate(Screen.DetailTaskScreen.route + "/" + task.id.toString()) })
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddTaskScreen.route) }, // Implement this function in your ViewModel
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            Text("+")
        }
    }
}

@Composable
fun TaskItem(
    task: Task, onDeleteClick: () -> Unit, onToggleCompleted: () -> Unit, onItemClick: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onItemClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(4f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(checked = task.completed, onCheckedChange = { onToggleCompleted() })

                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = "Delete Task"
                    )
                }
                // Confirmation dialog
                DeleteConfirmationDialog(
                    showDialog = showDialog.value,
                    onConfirmDelete = onDeleteClick,
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}