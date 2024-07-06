package com.example.todo.domain

import com.example.todo.data.repository.TaskRepository

class FetchTasksFromNetworkToDbUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke() {
        return taskRepository.fetchTasksFromNetworkAndUpdateDatabase()
    }
}