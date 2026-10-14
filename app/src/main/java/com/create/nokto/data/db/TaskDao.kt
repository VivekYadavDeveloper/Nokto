package com.create.nokto.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*Dao = Data Access Objects*/
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    @Update
    suspend fun update(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)


    @Query("SELECT * FROM tasks ORDER by id DESC")
    fun getAllTasks(): Flow<List<TaskItem>>

}