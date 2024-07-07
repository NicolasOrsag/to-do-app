package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.database.TaskDao
import com.example.todo.data.local.database.TaskDatabase
import com.example.todo.data.remote.TaskApi
import com.example.todo.data.remote.TaskApiImpl
import com.example.todo.data.repository.TaskRepository
import com.example.todo.domain.DeleteTaskFromDbUseCase
import com.example.todo.domain.FetchTasksFromNetworkToDbUseCase
import com.example.todo.domain.GetTaskByIdUseCase
import com.example.todo.domain.GetTasksFromDbUseCase
import com.example.todo.domain.InsetTaskToDbUseCase
import com.example.todo.domain.UpdateTaskCompletedUseCase
import com.example.todo.presentation.add_task.AddTaskViewModel
import com.example.todo.presentation.detail_task.DetailTaskViewModel
import com.example.todo.presentation.task_list.TaskListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide HttpClient
    single { provideHttpClient() }

    // Provide TaskApi
    single<TaskApi> { TaskApiImpl(get()) }

    // Provide Room Database
    single { provideDatabase(androidContext()) }

    // Provide TaskDao
    single { provideTaskDao(get()) }

    // Provide TaskRepository
    single { TaskRepository(get(), get()) }

    // Provide Use Cases
    single { GetTasksFromDbUseCase(get()) }
    single { FetchTasksFromNetworkToDbUseCase(get()) }
    single { InsetTaskToDbUseCase(get()) }
    single { DeleteTaskFromDbUseCase(get()) }
    single { UpdateTaskCompletedUseCase(get()) }
    single { GetTaskByIdUseCase(get()) }

    // Provide ViewModels
    viewModel { TaskListViewModel(get(), get(), get(), get()) }
    viewModel { AddTaskViewModel(get()) }
    viewModel { DetailTaskViewModel(get(), get()) }
}

fun provideHttpClient() = HttpClient(Android) {
    install(Logging) {
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        json()
    }
}


fun provideDatabase(context: Context) =
    Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "task_database")
        .build()

fun provideTaskDao(database: TaskDatabase): TaskDao = database.taskDao()
