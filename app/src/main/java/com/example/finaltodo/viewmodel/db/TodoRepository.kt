package com.example.finaltodo.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoRepository(private val toDoDao: ToDoDao) {

    fun getAllToDos(): LiveData<List<ToDo>> {
        return toDoDao.getAllToDos()
    }

    suspend fun insert(toDo: ToDo) {
        toDoDao.insert(toDo)
    }

    suspend fun delete(toDo: ToDo) {
        toDoDao.delete(toDo)
    }

    suspend fun update(toDo: ToDo) {
        toDoDao.update(toDo)
    }

    fun getToDoById(id: Int): LiveData<ToDo?> {
        return toDoDao.getToDoById(id)
    }
}
