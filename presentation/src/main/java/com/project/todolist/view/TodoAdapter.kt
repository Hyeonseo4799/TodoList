package com.project.todolist.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.domain.model.Todo
import com.project.todolist.databinding.ItemTodoBinding

class TodoAdapter(private val context: Context, private val clickListener: TodoClickListener) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {
    private var list = mutableListOf<Todo>()

    inner class TodoVH(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Todo) {
            binding.apply {
                item = data
                if (data.isChecked) todoTitle.paintFlags = todoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else todoTitle.paintFlags = todoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        init {
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVH {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false)
        return TodoVH(binding)
    }

    override fun onBindViewHolder(holder: TodoVH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: MutableList<Todo>) {
        this.list = newList
        notifyDataSetChanged()
    }
}

class TodoClickListener(val clickListener: (itemId: Long) -> Unit) {
    fun onClick(todo: Todo) = clickListener(todo.id)
}