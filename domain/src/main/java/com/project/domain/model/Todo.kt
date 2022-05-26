package com.project.domain.model

import java.io.Serializable

data class Todo(
    var id: Long = 0,
    val title: String,
    val content: String,
    val timestamp: String,
    var isChecked: Boolean
): Serializable