package com.project.domain.usecase

import androidx.lifecycle.LiveData
import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class ListUseCase(private val repository: TodoRepository) {
    fun invoke(): LiveData<MutableList<Todo>> = repository.list()
}