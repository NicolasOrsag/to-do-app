package com.example.todo.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Task
import com.example.todo.domain.DeleteTaskFromDbUseCase
import com.example.todo.domain.FetchTasksFromNetworkToDbUseCase
import com.example.todo.domain.GetTasksFromDbUseCase
import com.example.todo.domain.UpdateTaskCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTasksFromDbUseCase: GetTasksFromDbUseCase,
    private val fetchTasksFromNetworkToDbUseCase: FetchTasksFromNetworkToDbUseCase,
    private val deleteTaskFromDbUseCase: DeleteTaskFromDbUseCase,
    private val updateTaskCompletedUseCase: UpdateTaskCompletedUseCase
) : ViewModel() {
    val tasks = getTasksFromDbUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            fetchTasksFromNetworkToDbUseCase()
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            deleteTaskFromDbUseCase(task)
        }
    }

    fun toggleCompleted(task: Task){
        viewModelScope.launch {
            updateTaskCompletedUseCase(task)
        }
    }
}