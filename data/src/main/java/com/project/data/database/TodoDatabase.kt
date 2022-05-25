package com.project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.data.dao.TodoDao
import com.project.data.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}