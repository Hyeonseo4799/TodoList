package com.project.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.data.model.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("SELECT * FROM todoTable")
    fun list(): LiveData<MutableList<Todo>>

    @Query("SELECT * FROM todoTable WHERE id = :id")
    fun selectOne(id: Long): Todo

    @Update
    suspend fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}