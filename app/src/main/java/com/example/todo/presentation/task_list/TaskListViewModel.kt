package com.example.todo.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.FetchTasksFromNetworkToDbUseCase
import com.example.todo.domain.GetTasksFromDbUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTasksFromDbUseCase: GetTasksFromDbUseCase,
    private val fetchTasksFromNetworkToDbUseCase: FetchTasksFromNetworkToDbUseCase
) : ViewModel() {
    val tasks = getTasksFromDbUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            fetchTasksFromNetworkToDbUseCase()
        }
    }
}