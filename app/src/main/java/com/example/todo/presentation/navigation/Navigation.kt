package com.example.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.presentation.add_task.AddTaskScreen
import com.example.todo.presentation.task_list.TaskListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.TaskListScreen.route
    ) {
        composable(route = Screen.TaskListScreen.route) {
            TaskListScreen(navController = navController)
        }
        composable(route = Screen.AddTaskScreen.route) {
            AddTaskScreen(navController = navController)
        }
    }
}