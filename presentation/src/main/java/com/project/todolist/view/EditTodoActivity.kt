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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@EditTodoActivity, R.layout.activity_edit_todo)
        binding.activity = this@EditTodoActivity
        type = intent.getStringExtra("type")!!
        btnText = if (type == "ADD") "추가하기" else "수정하기"
    }

    @SuppressLint("SimpleDateFormat")
    fun save() {
        val title = binding.todoTitle.text.toString()
        val content = binding.todoContent.text.toString()
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())

        when (type) {
            "ADD" -> {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val todo = Todo(0, title, content, currentDate, false)
                    val intent = Intent().apply {
                        putExtra("todo", todo)
                        putExtra("flag", 0)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    // 수정
                }
            }
        }
    }
}