package com.example.todo.data.remote

import android.util.Log
import com.example.todo.data.remote.dto.TaskDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TaskApiImpl(
    private val client: HttpClient
) : TaskApi {

    override suspend fun getTasks(): List<TaskDto> {
        return try {
            client.get(HttpRoutes.TASKS).body()
        } catch (e: Exception) {
            Log.e("Ktor client error", "Ktor client error message: ${e.message}")
            emptyList()
        }
    }
}