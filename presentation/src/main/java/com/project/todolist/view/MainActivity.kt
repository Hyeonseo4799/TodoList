package com.project.todolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.domain.model.Todo
import com.project.todolist.R
import com.project.todolist.databinding.ActivityMainBinding
import com.project.todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val todo = it.data?.getSerializableExtra("todo") as Todo
            Log.d("todo", todo.toString())
            when (it.data?.getIntExtra("flag", -1)) {
                0 -> {
                    Log.d("todo", it.data?.getIntExtra("flag", -1).toString())
                    CoroutineScope(Dispatchers.IO).launch { todoViewModel.insert(todo) }
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.activity = this@MainActivity
        binding.lifecycleOwner = this@MainActivity

        setRec()

        todoViewModel.todoList.observe(this@MainActivity) { todoAdapter.update(it) }
    }

    fun add() {
        val intent = Intent(this, EditTodoActivity::class.java)
        intent.putExtra("type", "ADD")
        requestActivity.launch(intent)
    }

    private fun setRec() {
        todoAdapter = TodoAdapter(this@MainActivity, TodoClickListener { id -> onClick(id) })
        todoAdapter.setHasStableIds(true)
        binding.apply {
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodoList.adapter = todoAdapter
            rvTodoList.itemAnimator = null
        }

    }

    private fun onClick(itemId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val todo = todoViewModel.getTodo(itemId)
            Log.d("todo", todo.toString())
            todo.isChecked = !todo.isChecked
            Log.d("todo", todo.toString())
            todoViewModel.update(todo)
        }
    }
}