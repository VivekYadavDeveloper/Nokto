package com.create.nokto.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.create.nokto.data.db.TaskItem
import com.create.nokto.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorDialog(

    task: TaskItem?,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit,
) {

    var taskTitle by remember { mutableStateOf(task?.title ?: " ") }
    var taskDescription by remember { mutableStateOf(task?.description ?: "") }

    ModalBottomSheet(onDismissRequest = onCancel, containerColor = Color.White) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .navigationBarsPadding()
        ) {
            Text(
                if (task == null) "Create New Task" else "Update Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("What's need to be done?") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = Color.Gray
                ), shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Description") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = Color.Gray
                ), shape = RoundedCornerShape(16.dp)

            )
            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                    onSave(taskTitle.trim(), taskDescription.trim())

                },
                enabled = taskTitle.isNotBlank() && taskDescription.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(primaryColor),
                shape = RoundedCornerShape(16.dp)
            ) { Text("Save Task") }
        }
    }
}