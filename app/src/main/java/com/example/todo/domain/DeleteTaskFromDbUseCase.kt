package com.example.todo.domain

import com.example.todo.data.local.model.Task
import com.example.todo.data.repository.TaskRepository

class DeleteTaskFromDbUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.deleteTask(task)
}