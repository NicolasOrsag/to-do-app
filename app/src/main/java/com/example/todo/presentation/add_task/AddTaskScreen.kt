package com.example.todo.presentation.add_task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTaskScreen(navController: NavController, viewModel: AddTaskViewModel = koinViewModel()) {
    val taskTitle = remember { mutableStateOf("") }
    val taskDescription = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Replace with your desired background color
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Add New Task",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Task Title",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(value = taskTitle.value,
                    onValueChange = { taskTitle.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 4.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = "Enter task title", color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Description (Optional)",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(value = taskDescription.value,
                    onValueChange = { taskDescription.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 4.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Enter task descrition",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.saveTask(taskTitle.value, taskDescription.value)
                        navController.popBackStack()
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Save Task", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

