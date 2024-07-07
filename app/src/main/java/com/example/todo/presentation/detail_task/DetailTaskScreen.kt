package com.example.todo.presentation.detail_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.presentation.components.DeleteConfirmationDialog
import com.example.todo.utils.DateTimeUtils
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailTaskScreen(
    navController: NavController, taskId: Int, viewModel: DetailTaskViewModel = koinViewModel()
) {
    val task by viewModel.task.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Adjust to your desired background color
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    task?.let { task ->
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Date of creation: ${DateTimeUtils.formatDate(task.createdAt)}",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Description:",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = task.description ?: "No description provided",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Status:",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (task.completed) "Completed" else "Incomplete",
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showDialog.value = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(text = "Delete")
                        }
                        // Confirmation dialog
                        DeleteConfirmationDialog(
                            showDialog = showDialog.value,
                            onConfirmDelete = {
                                viewModel.deleteTask(task)
                                navController.popBackStack()
                            },
                            onDismiss = { showDialog.value = false }
                        )
                    } ?: run {
                        Text(
                            text = "Task not found",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
        }
    }
}