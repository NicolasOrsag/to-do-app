package com.example.todo.domain

import com.example.todo.data.local.model.Task
import com.example.todo.data.repository.TaskRepository

class GetTasksFromDbUseCase(private val repository: TaskRepository) {
    operator fun invoke() = repository.getTasks()
}