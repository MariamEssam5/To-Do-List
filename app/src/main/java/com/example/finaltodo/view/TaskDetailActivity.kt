package com.example.finaltodo.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.finaltodo.R
import com.example.finaltodo.viewmodel.ToDoViewModel

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var toDoViewModel: ToDoViewModel
    private lateinit var textViewTitle: TextView
    private lateinit var textViewDescription: TextView  // Add a TextView for description

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val taskId = intent.getIntExtra("TASK_ID", -1)
        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)  // Initialize description TextView

        toDoViewModel.getToDoById(taskId).observe(this) { todo ->
            todo?.let {
                textViewTitle.text = it.title
                textViewDescription.text = it.description  // Display description
            }
        }
    }
}
