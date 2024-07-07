package com.example.todo.presentation.navigation

sealed class Screen(val route: String) {
    data object TaskListScreen : Screen("task_list_screen")
    data object AddTaskScreen : Screen("add_task_screen")
    data object DetailTaskScreen : Screen("detail_task_screen")
}