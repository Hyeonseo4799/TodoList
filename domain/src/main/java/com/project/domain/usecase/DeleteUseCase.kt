package com.project.domain.usecase

import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class DeleteUseCase(private val repository: TodoRepository) {
    fun invoke(todo: Todo) {
        repository.delete(todo)
    }
}