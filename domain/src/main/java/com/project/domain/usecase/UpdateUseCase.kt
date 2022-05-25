package com.project.domain.usecase

import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class UpdateUseCase(private val repository: TodoRepository) {
    suspend fun invoke(todo: Todo) {
        repository.update(todo)
    }
}