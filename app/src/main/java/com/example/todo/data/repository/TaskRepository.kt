package com.example.todo.data.repository

import com.example.todo.data.local.database.TaskDao
import com.example.todo.data.local.model.Task
import com.example.todo.data.remote.TaskApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val api: TaskApi, private val taskDao: TaskDao) {
    suspend fun fetchTasksFromNetworkAndUpdateDatabase() {
        withContext(Dispatchers.IO) {
            val tasksFromNetwork = api.getTasks()
            val tasks = tasksFromNetwork.map {
                Task(
                    title = it.title,
                    description = it.description,
                    completed = it.completed,
                    createdAt = it.createdAt
                )
            }
            taskDao.deleteAllTasks()
            taskDao.insertTasks(tasks)
        }
    }

    fun getTasks() = taskDao.getAllTasks()

    suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) = withContext(Dispatchers.IO) {
        taskDao.deleteTask(task)
    }
}