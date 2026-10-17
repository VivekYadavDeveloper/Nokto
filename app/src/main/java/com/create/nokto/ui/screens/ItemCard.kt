package com.create.nokto.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.create.nokto.data.db.TaskItem
import com.create.nokto.ui.theme.primaryColor
import com.create.nokto.ui.theme.redColor

@Composable
fun ItemCard(

    item: TaskItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckChange: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(if (item.isDone) primaryColor else Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onCheckChange(!item.isDone) },
                modifier = Modifier.size(24.dp)

            ) {
                Icon(
                    imageVector = if (!item.isDone) Icons.Default.RadioButtonUnchecked else Icons.Default.RadioButtonChecked,
                    contentDescription = null,
                    tint = if (!item.isDone) Color.White else primaryColor
                )
            }

            Text(
                item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier.weight(1f),
                color = if (item.isDone) Color.Black else Color.Gray
            )

            Row() {
                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Task", tint = primaryColor

                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task",
                        tint = redColor
                    )
                }
            }
        }
    }
}

/*1:17:00*/