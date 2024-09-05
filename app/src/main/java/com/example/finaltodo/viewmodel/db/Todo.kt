package com.example.finaltodo.viewmodel.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")


data class ToDo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val title: String,
    val description: String,  // Add a description field
    //val color: Int, // Add a color property

    var isCompleted: Boolean=false
)