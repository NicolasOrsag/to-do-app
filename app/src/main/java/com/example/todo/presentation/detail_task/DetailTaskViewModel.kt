package com.example.todo.presentation.detail_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Task
import com.example.todo.domain.DeleteTaskFromDbUseCase
import com.example.todo.domain.GetTaskByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailTaskViewModel(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val deleteTaskFromDbUseCase: DeleteTaskFromDbUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _task = MutableStateFlow<Task?>(null)
    val task = _task.asStateFlow()

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            val result = async(Dispatchers.IO) {
                getTaskByIdUseCase(taskId)
            }
            val data = result.await()
            _isLoading.value = false
            _task.value = data
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskFromDbUseCase(task)
        }
    }
}