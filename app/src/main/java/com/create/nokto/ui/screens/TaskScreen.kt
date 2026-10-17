package com.create.nokto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.create.nokto.data.db.TaskItem
import com.create.nokto.ui.theme.primaryColor
import com.create.nokto.viewmodel.TaskViewModel


@Composable
fun TodoListScreen(viewModel: TaskViewModel) {

    val tasks by viewModel.allTasks.collectAsState()
    var tasksToEdit by remember { mutableStateOf<TaskItem?>(null) }
    var showDialogToEdit by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    tasksToEdit = null
                    showDialogToEdit = true
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = primaryColor,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                )
                Text("New Task", modifier = Modifier.padding(start = 8.dp))
            }
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                "My Tasks", modifier = Modifier.padding(top = 32.dp), fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )

            Text("You have ${tasks.filter { !it.isDone }.size} remaining tasks")

            Spacer(modifier = Modifier.height(20.dp))

            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { Text("No Task") }

            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 18.dp)

                ) {
                    items(tasks, key = { it.id }) { index ->

                        ItemCard(
                            item = index,
                            onEditClick = {
                                tasksToEdit = index
                                showDialogToEdit = true
                            },
                            onDeleteClick = { viewModel.deleteTask(index) },
                            onCheckChange = { isChecked ->
                                viewModel.updateTask(index.copy(isDone = isChecked))
                            }
                        )
                    }


                }

            }
        }
    }


    if (showDialogToEdit) {
        TaskEditorDialog(
            task = tasksToEdit,
            onSave = { newName, newDescription ->
                if (tasksToEdit == null) {
                    viewModel.addTask(
                        TaskItem(
                            title = newName,
                            isDone = false,
                            description = newDescription
                        )
                    )
                } else {

                    tasksToEdit?.let {
                        viewModel.updateTask(
                            it.copy(
                                title = newName,
                                description = newDescription
                            )
                        )
                    }
                }
                showDialogToEdit = false
                tasksToEdit = null
            },
            onCancel = {
                showDialogToEdit = false
                tasksToEdit = null
            }
        )
    }
}