package com.create.nokto.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String,
    val idDone: Boolean = false,
)
