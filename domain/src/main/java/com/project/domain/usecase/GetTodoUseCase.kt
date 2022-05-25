package com.project.domain.usecase

import com.project.domain.repository.TodoRepository

class GetTodoUseCase(private val repository: TodoRepository) {
    fun invoke(id: Long) {
        repository.getTodo(id)
    }
}