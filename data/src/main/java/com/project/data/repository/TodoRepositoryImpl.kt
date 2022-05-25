package com.project.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.project.data.database.TodoDatabase
import com.project.data.model.TodoResponse
import com.project.domain.model.Todo
import com.project.domain.repository.TodoRepository
import java.lang.IllegalStateException

interface TodoDataSource {
    fun insert(todoResponse: TodoResponse)
    fun getTodo(id: Long): TodoResponse
    fun list(): LiveData<MutableList<TodoResponse>>
    suspend fun update(todoResponse: TodoResponse)
    fun delete(todoResponse: TodoResponse)
}

class TodoRepositoryImpl(context: Context) : TodoDataSource {

    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        "todo-database.db"
    ).build()

    private val todoDao = database.todoDao()
    override fun list(): LiveData<MutableList<TodoResponse>> = todoDao.list()

    override fun getTodo(id: Long): TodoResponse = todoDao.selectOne(id)

    override fun insert(todoResponse: TodoResponse) = todoDao.insert(todoResponse)

    override suspend fun update(todoResponse: TodoResponse) = todoDao.update(todoResponse)

    override fun delete(todoResponse: TodoResponse) = todoDao.delete(todoResponse)

    companion object {
        private var INSTANCE: TodoRepositoryImpl? = null

        fun initialize(context: Context) {
            if (INSTANCE == null)
                INSTANCE = TodoRepositoryImpl(context)
        }

        fun get(): TodoRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("TodoRepository muse be initialized")
        }
    }
}