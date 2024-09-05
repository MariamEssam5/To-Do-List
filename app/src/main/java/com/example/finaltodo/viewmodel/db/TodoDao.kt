package com.example.finaltodo.viewmodel.db


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)

    @Update
    suspend fun update(toDo: ToDo)
    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun getToDoById(id: Int): LiveData<ToDo?>
}
