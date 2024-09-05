package com.example.finaltodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finaltodo.view.db.ToDo
import com.example.finaltodo.view.db.ToDoDatabase
import com.example.finaltodo.view.db.TodoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    val allToDos: LiveData<List<ToDo>>

    init {
        val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
        repository = TodoRepository(toDoDao)
        allToDos = repository.getAllToDos()
    }

    fun insert(toDo: ToDo) = viewModelScope.launch {
        repository.insert(toDo)
    }

    fun delete(toDo: ToDo) = viewModelScope.launch {
        repository.delete(toDo)
    }

    fun update(toDo: ToDo) = viewModelScope.launch {
        repository.update(toDo)
    }

    fun getToDoById(id: Int): LiveData<ToDo?> {
        return repository.getToDoById(id)
    }
}
