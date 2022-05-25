package com.project.domain.usecase

import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class InsertUseCase(private val repository: TodoRepository) {
    fun invoke(todo: Todo) {
        repository.insert(todo)
    }
}