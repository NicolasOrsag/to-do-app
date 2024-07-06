package com.example.todo.data.remote

import com.example.todo.data.remote.dto.TaskDto

interface TaskApi {

    suspend fun getTasks(): List<TaskDto>

}