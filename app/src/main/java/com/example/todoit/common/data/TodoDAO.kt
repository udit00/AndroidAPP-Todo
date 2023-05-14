package com.example.todoit.common.data

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface TodoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(todo: Todo)

    @Query("Select * FROM todo_table")
    fun read(): LiveData<List<Todo>>
}