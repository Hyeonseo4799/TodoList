package com.project.data.datasource

import androidx.lifecycle.LiveData
import com.project.data.model.TodoResponse

interface TodoDataSource {
    fun insert(todoResponse: TodoResponse)
    fun getTodo(id: Long): TodoResponse
    fun list(): LiveData<MutableList<TodoResponse>>
    suspend fun update(todoResponse: TodoResponse)
    fun delete(todoResponse: TodoResponse)
}