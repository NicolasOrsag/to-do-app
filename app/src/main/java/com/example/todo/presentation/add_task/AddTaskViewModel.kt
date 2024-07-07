package com.example.todo.presentation.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Task
import com.example.todo.domain.InsetTaskToDbUseCase
import com.example.todo.utils.DateTimeUtils
import kotlinx.coroutines.launch

class AddTaskViewModel(private val insetTaskToDbUseCase: InsetTaskToDbUseCase) : ViewModel() {
    fun saveTask(title: String, description: String) {
        viewModelScope.launch {
            insetTaskToDbUseCase(title, description.ifEmpty { null })
        }

    }
}