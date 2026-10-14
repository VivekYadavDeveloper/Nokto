package com.create.nokto.Repo

import com.create.nokto.data.db.TaskDao
import com.create.nokto.data.db.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    fun getAllTask(): Flow<List<TaskItem>> {
        return dao.getAllTasks()
    }


    suspend fun insert(task: TaskItem) {
        return dao.insert(task)
    }


    suspend fun update(task: TaskItem) {
        return dao.update(task)
    }

    suspend fun delete(task: TaskItem) {
        return dao.delete(task)
    }
}


