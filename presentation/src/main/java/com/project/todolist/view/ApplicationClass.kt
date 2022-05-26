package com.project.todolist.view

import android.app.Application
import com.project.data.datasource.TodoDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        TodoDataSourceImpl.initialize(this)

        startKoin {
            androidContext(this@ApplicationClass)
            modules(updateModule, listModule, insertModule, getTodoModule, todoDataSourceModule, todoRepositoryModule, deleteModule, viewModelModule)
        }
    }
}