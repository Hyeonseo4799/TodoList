package com.project.domain.usecase

import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class GetTodoUseCase(private val repository: TodoRepository) {
    fun invoke(id: Long): Todo = repository.getTodo(id)
}