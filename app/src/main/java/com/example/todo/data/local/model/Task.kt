package com.example.todo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val createdAt: String,
    val title: String,
    val description: String?,
    val completed: Boolean
)
