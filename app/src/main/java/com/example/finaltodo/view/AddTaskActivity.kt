package com.example.finaltodo.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.finaltodo.R
import com.example.finaltodo.viewmodel.ToDoViewModel
import com.example.finaltodo.view.db.ToDo

class AddTaskActivity : AppCompatActivity() {

    private lateinit var toDoViewModel: ToDoViewModel
    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        editTextTitle = findViewById(R.id.editTextTaskTitle)
        editTextDescription = findViewById(R.id.editTextTaskDescription)

        findViewById<Button>(R.id.buttonSaveTask).setOnClickListener {
            val taskTitle = editTextTitle.text.toString()
            val taskDescription = editTextDescription.text.toString()
            if (taskTitle.isNotBlank()) {
                toDoViewModel.insert(
                    ToDo(
                    id = 0,
                    title = taskTitle,
                    description = taskDescription,
                    isCompleted = false
                )
                )
                finish()
            }
        }
    }
}