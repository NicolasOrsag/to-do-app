package com.example.todo.domain

import com.example.todo.data.local.model.Task
import com.example.todo.data.repository.TaskRepository

class InsetTaskToDbUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.insertTask(task)
}