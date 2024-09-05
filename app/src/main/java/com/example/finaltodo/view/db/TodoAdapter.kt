package com.example.finaltodo.view.db

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltodo.R

class ToDoAdapter(
    private val onDeleteClick: (ToDo) -> Unit,          // Callback for delete action
    private val onTaskComplete: (ToDo) -> Unit          // Callback for task completion
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var todoList = emptyList<ToDo>()

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.todo_title)
        val description: TextView = itemView.findViewById(R.id.todo_description)
        val linearTask: View = itemView.findViewById(R.id.linearTask)
        private val deleteButton: Button = itemView.findViewById(R.id.button_delete)
        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox_complete)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val todo = todoList[position]
                    onDeleteClick(todo) // Trigger the delete callback
                }
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val todo = todoList[position]
                    todo.isCompleted = isChecked // Update the task completion status
                    onTaskComplete(todo) // Trigger the complete callback
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todoList[position]
        holder.title.text = currentToDo.title
        holder.description.text = currentToDo.description

        // Set the checkbox state based on the completion status
        holder.checkbox.isChecked = currentToDo.isCompleted

        // Change background color based on title using hex codes
        when {
            currentToDo.title.contains("Work", ignoreCase = true) -> {
                holder.linearTask.setBackgroundColor(Color.parseColor("#E6E6FA")) // DodgerBlue
            }
            currentToDo.title.contains("Personal", ignoreCase = true) -> {
                holder.linearTask.setBackgroundColor(Color.parseColor("#00FFFF")) // DimGray
            }
            currentToDo.title.contains("Home", ignoreCase = true) -> {
                holder.linearTask.setBackgroundColor(Color.parseColor("#98FF98")) // LimeGreen
            }
            else -> {
                // Default background color
                holder.linearTask.setBackgroundColor(Color.parseColor("#FFFFFF")) // White
            }
        }
    }

    override fun getItemCount() = todoList.size

    fun setData(todos: List<ToDo>) {
        this.todoList = todos
        notifyDataSetChanged()
    }
}