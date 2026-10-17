package com.create.nokto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.create.nokto.ui.screens.TodoListScreen

import com.create.nokto.ui.theme.NoktoTheme
import com.create.nokto.viewmodel.TaskViewModel
import com.create.nokto.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels { TaskViewModelFactory(application) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoktoTheme {
                TodoListScreen(viewModel)
            }
        }
    }
}

