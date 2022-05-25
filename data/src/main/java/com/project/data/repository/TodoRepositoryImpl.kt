package com.project.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.project.data.datasource.TodoDataSource
import com.project.data.mapper.TodoMapper
import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository

class TodoRepositoryImpl(private val todoDataSource: TodoDataSource) : TodoRepository {
    override fun list(): LiveData<MutableList<Todo>> {
        return Transformations.map(todoDataSource.list()) { mutableListTodoRes ->
            mutableListTodoRes.map {
                TodoMapper.mapperToTodo(it)
            }.toMutableList()
        }
    }

    override fun getTodo(id: Long): Todo = TodoMapper.mapperToTodo(todoDataSource.getTodo(id))

    override fun insert(todo: Todo) {
        todoDataSource.insert(TodoMapper.mapperToTodoResponse(todo))
    }

    override suspend fun update(todo: Todo) {
        todoDataSource.update(TodoMapper.mapperToTodoResponse(todo))
    }

    override fun delete(todo: Todo) {
        todoDataSource.delete(TodoMapper.mapperToTodoResponse(todo))
    }

}