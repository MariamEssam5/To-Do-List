package com.example.finaltodo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltodo.R
import com.example.finaltodo.viewmodel.ToDoViewModel
import com.example.finaltodo.view.db.ToDoAdapter

class CompletedTasksFragment : Fragment() {

    private lateinit var toDoViewModel: ToDoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDoAdapter = ToDoAdapter(
            onDeleteClick = { toDo ->
                toDoViewModel.delete(toDo)
            },
            onTaskComplete = { toDo ->
                toDoViewModel.update(toDo)  // Update the task as completed
            }
        )

        recyclerView = view.findViewById(R.id.recyclerViewCompletedTasks)
        recyclerView.adapter = toDoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        toDoViewModel.allToDos.observe(viewLifecycleOwner, { todos ->
            todos?.let {
                val completedTasks = it.filter { task -> task.isCompleted }
                toDoAdapter.setData(completedTasks)
                view.findViewById<TextView>(R.id.tvNoCompletedTasks).visibility =
                    if (completedTasks.isEmpty()) View.VISIBLE else View.GONE
            }
        })
    }
}
