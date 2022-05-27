package com.project.todolist.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.domain.model.Todo
import com.project.todolist.R
import com.project.todolist.databinding.ActivityEditTodoBinding
import org.koin.core.context.startKoin
import java.text.SimpleDateFormat

class EditTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTodoBinding
    lateinit var type: String
    lateinit var btnText: String
    var title = ""
    var content = ""
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@EditTodoActivity, R.layout.activity_edit_todo)
        binding.activity = this@EditTodoActivity
        type = intent.getStringExtra("type")!!
        when (type) {
            "ADD" -> btnText = "추가하기"
            "EDIT" -> {
                todo = intent.getSerializableExtra("item") as Todo?
                title = todo!!.title
                content = todo!!.content
                btnText = "수정하기"
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun save() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())
        val title = binding.todoTitle.text.toString()
        val content = binding.todoContent.text.toString()
        when (type) {
            "ADD" -> {
                if (emptyCheck(title, content)) {
                    val todo = Todo(0, title, content, currentDate, false)
                    val intent = Intent().apply {
                        putExtra("todo", todo)
                        putExtra("flag", 0)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            "EDIT" -> {
                if (emptyCheck(title, content)) {
                    val todo = Todo(todo!!.id, title, content, currentDate, todo!!.isChecked)
                    val intent = Intent().apply {
                        putExtra("todo", todo)
                        putExtra("flag", 1)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}

private fun emptyCheck(title: String, content: String): Boolean = title.isNotEmpty() && content.isNotEmpty()