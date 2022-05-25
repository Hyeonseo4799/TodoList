package com.project.domain.repository

import androidx.lifecycle.LiveData
import com.project.domain.model.Todo

interface TodoRepository {
    fun list(): LiveData<MutableList<Todo>>
    fun getTodo(id: Long): Todo
    fun insert(todo: Todo)
    suspend fun update(todo: Todo)
    fun delete(todo: Todo)
}