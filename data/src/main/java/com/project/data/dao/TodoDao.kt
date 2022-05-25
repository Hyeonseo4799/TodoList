package com.project.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.data.model.TodoResponse

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todoResponse: TodoResponse)

    @Query("SELECT * FROM todoTable")
    fun list(): LiveData<MutableList<TodoResponse>>

    @Query("SELECT * FROM todoTable WHERE id = :id")
    fun selectOne(id: Long): TodoResponse

    @Update
    suspend fun update(todoResponse: TodoResponse)

    @Delete
    fun delete(todoResponse: TodoResponse)
}