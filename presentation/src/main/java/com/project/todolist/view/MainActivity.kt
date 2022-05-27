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
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val todo = it.data?.getSerializableExtra("todo") as Todo
            when (it.data?.getIntExtra("flag", -1)) {
                0 -> {
                    CoroutineScope(Dispatchers.IO).launch { todoViewModel.insert(todo) }
                    Toast.makeText(this@MainActivity, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    CoroutineScope(Dispatchers.IO).launch { todoViewModel.update(todo) }
                    Toast.makeText(this@MainActivity, "수정되었습니다.", Toast.LENGTH_SHORT).show()
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
        todoAdapter = TodoAdapter(this@MainActivity,
            CheckBoxClickListener { id -> cbClick(id) },
            ItemClickListener { id -> itemClick(id) })

        todoAdapter.setHasStableIds(true)
        binding.apply {
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodoList.adapter = todoAdapter
            rvTodoList.itemAnimator = null
        }

    }

    private fun cbClick(itemId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val todo = todoViewModel.getTodo(itemId)
            todo.isChecked = !todo.isChecked
            todoViewModel.update(todo)
        }
    }

    private fun itemClick(itemId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val todo = todoViewModel.getTodo(itemId)
            val intent = Intent(this@MainActivity, EditTodoActivity::class.java).apply {
                putExtra("type", "EDIT")
                putExtra("item", todo)
            }
            requestActivity.launch(intent)
        }
    }
}