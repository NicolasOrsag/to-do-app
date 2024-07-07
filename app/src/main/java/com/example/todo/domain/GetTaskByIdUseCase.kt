package com.example.todo.domain

import com.example.todo.data.repository.TaskRepository

class GetTaskByIdUseCase (private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: Int) = repository.getTask(taskId)
}