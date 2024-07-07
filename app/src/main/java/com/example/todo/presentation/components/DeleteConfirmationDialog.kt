package com.example.todo.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeleteConfirmationDialog(
    showDialog: Boolean,
    onConfirmDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { onDismiss() },
            title = {
                Text("Confirm Delete")
            },
            text = {
                Text("Are you sure you want to delete this task?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmDelete()
                        onDismiss()
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
