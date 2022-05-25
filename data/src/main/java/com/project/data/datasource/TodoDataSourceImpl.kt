package com.project.data.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.project.data.database.TodoDatabase
import com.project.data.model.TodoResponse
import java.lang.IllegalStateException

class TodoDataSourceImpl(context: Context) : TodoDataSource {
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
        private var INSTANCE: TodoDataSourceImpl? = null

        fun initialize(context: Context) {
            if (INSTANCE == null)
                INSTANCE = TodoDataSourceImpl(context)
        }

        fun get(): TodoDataSourceImpl {
            return INSTANCE ?: throw IllegalStateException("TodoDataSourceImpl muse be initialized")
        }
    }
}