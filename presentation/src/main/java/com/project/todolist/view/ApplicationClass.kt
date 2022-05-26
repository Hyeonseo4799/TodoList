package com.project.todolist.view

import android.app.Application
import com.project.data.datasource.TodoDataSourceImpl

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()

        TodoDataSourceImpl.initialize(this)
    }
}