package com.example.todo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val createdAt: String,
    val title: String,
    val description: String?,
    val completed: Boolean,
    val id: String
)
