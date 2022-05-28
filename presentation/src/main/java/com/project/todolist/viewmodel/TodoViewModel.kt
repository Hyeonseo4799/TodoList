package com.project.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.Todo
import com.project.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(
    private val deleteUseCase: DeleteUseCase,
    private val getTodoUseCase: GetTodoUseCase,
    private val insertUseCase: InsertUseCase,
    private val listUseCase: ListUseCase,
    private val updateUseCase: UpdateUseCase
) : ViewModel() {
    val todoList: LiveData<MutableList<Todo>>

    init {
        todoList = list()
    }

    fun delete(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase.invoke(todo)
        }
    }

    fun getTodo(id: Long): Todo = getTodoUseCase.invoke(id)

    fun insert(todo: Todo) {
        insertUseCase.invoke(todo)
    }

    private fun list(): LiveData<MutableList<Todo>> = listUseCase.invoke()

    fun update(todo: Todo) {
        viewModelScope.launch {
            updateUseCase.invoke(todo)
        }
    }
}