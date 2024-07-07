package com.example.todo.domain

import com.example.todo.data.local.model.Task
import com.example.todo.data.repository.TaskRepository
import com.example.todo.utils.DateTimeUtils

class InsetTaskToDbUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(title: String, description: String?) {
        repository.insertTask(
            Task(
                title = title,
                description = description,
                completed = false,
                createdAt = DateTimeUtils.getCurrentDateTimeString()
            )
        )
    }
}