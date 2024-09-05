package com.example.finaltodo.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltodo.R
import com.example.finaltodo.viewmodel.ToDoViewModel
import com.example.finaltodo.view.db.ToDoAdapter

class PersonalTodosFragment : Fragment() {

    private lateinit var toDoViewModel: ToDoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var btnAddTask: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_todos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter with delete and complete callbacks
        toDoAdapter = ToDoAdapter(
            onDeleteClick = { toDo ->
                // Handle delete action
                toDoViewModel.delete(toDo)
            },
            onTaskComplete = { toDo ->
                // Handle task completion
                toDo.isCompleted = true
                toDoViewModel.update(toDo) // Update the task in the ViewModel
            }
        )

        recyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerView.adapter = toDoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        toDoViewModel.allToDos.observe(viewLifecycleOwner, { todos ->
            todos?.let {
                // Filter tasks based on the "Personal" keyword
                val personalTodos = it.filter { todo ->
                    todo.title.contains("Personal", ignoreCase = true)
                }
                toDoAdapter.setData(personalTodos)
            }
        })

        btnAddTask = view.findViewById(R.id.btnAddTask)
        btnAddTask.setOnClickListener {
            // Handle add task button click
            val intent = Intent(requireContext(), AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
}
