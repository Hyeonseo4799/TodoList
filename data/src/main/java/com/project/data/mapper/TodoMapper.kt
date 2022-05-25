package com.project.data.mapper

import com.project.data.model.TodoResponse
import com.project.domain.model.Todo

object TodoMapper {
    fun mapperToTodo(todoResponse: TodoResponse): Todo {
        return Todo(
            id = todoResponse.id,
            title = todoResponse.title,
            content = todoResponse.content,
            timestamp = todoResponse.timestamp,
            isChecked = todoResponse.isChecked
        )
    }

    fun mapperToTodoResponse(todo: Todo): TodoResponse {
        return TodoResponse(
            id = todo.id,
            title = todo.title,
            content = todo.content,
            timestamp = todo.timestamp,
            isChecked = todo.isChecked
        )
    }
}