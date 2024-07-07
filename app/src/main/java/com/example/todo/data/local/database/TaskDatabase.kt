package com.example.todo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.local.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}