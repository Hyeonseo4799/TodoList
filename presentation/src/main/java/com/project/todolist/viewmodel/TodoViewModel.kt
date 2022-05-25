package com.project.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.Todo
import com.project.domain.usecase.DeleteUseCase
import com.project.domain.usecase.ListUseCase
import com.project.domain.usecase.UpdateUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val deleteUseCase: DeleteUseCase,
    private val getTodoUseCase: DeleteUseCase,
    private val insertUseCase: DeleteUseCase,
    private val listUseCase: ListUseCase,
    private val updateUseCase: UpdateUseCase
) : ViewModel() {
    fun delete(todo: Todo) {
        deleteUseCase.invoke(todo)
    }

    fun getTodo(todo: Todo) {
        getTodoUseCase.invoke(todo)
    }

    fun insert(todo: Todo) {
        insertUseCase.invoke(todo)
    }

    fun list(): LiveData<MutableList<Todo>> = listUseCase.invoke()

    fun update(todo: Todo) {
        viewModelScope.launch {
            updateUseCase.invoke(todo)
        }
    }
}