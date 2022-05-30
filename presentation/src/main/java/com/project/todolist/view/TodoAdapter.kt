package com.project.todolist.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.domain.model.Todo
import com.project.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private val context: Context, private val clickListener: CheckBoxClickListener, private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {
    private var list = mutableListOf<Todo>()

    inner class TodoVH(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Todo) {
            binding.apply {
                item = data
                when (data.isChecked) {
                    true -> todoTitle.paintFlags = todoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    false -> todoTitle.paintFlags = todoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

        init {
            binding.cbClickListener = clickListener
            binding.itemClickListner = itemClickListener
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

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: MutableList<Todo>) {
        this.list = newList
        notifyDataSetChanged()
    }
}

class CheckBoxClickListener(val clickListener: (itemId: Long) -> Unit) {
    fun cbClick(todo: Todo) = clickListener(todo.id)
}

class ItemClickListener(val itemClickListener: (itemid: Long) -> Unit) {
    fun itemClick(todo: Todo) = itemClickListener(todo.id)
}