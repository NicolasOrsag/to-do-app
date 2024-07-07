package com.example.todo.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.presentation.add_task.AddTaskScreen
import com.example.todo.presentation.detail_task.DetailTaskScreen
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
        composable(
            Screen.DetailTaskScreen.route + "/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId")
            if(taskId != null){
                DetailTaskScreen(navController, taskId)
            }
            else{
                Log.e("Error", "TaskId for DetailTaskScreen is null")
            }
        }
    }
}