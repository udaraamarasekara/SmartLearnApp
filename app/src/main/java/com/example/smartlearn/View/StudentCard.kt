package com.example.smartlearn.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartlearn.Model.StudentAndTutorData

@Composable
fun StudentCard(student: StudentAndTutorData, onDelete: (id: Int) -> Unit,onApprove:(id:Int)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .defaultMinSize(minHeight = 140.dp)
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Name: ${student.name}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Email: ${student.email}", style = MaterialTheme.typography.bodyMedium)
                Row {
                  if(student.is_approved==0){  Button(
                        onClick = {
                            onApprove(student.id)
                        },
                        modifier = Modifier.padding(
                            5.dp,
                            5.dp,
                            0.dp,
                            0.dp
                        )
                    ) { Text(text = "Approve Student") }
                   }
                    Button(
                        onClick = {
                            onDelete(student.id)
                        },
                        modifier = Modifier.padding(
                            5.dp,
                            5.dp,
                            0.dp,
                            0.dp
                        ), colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, // Background color of the button
                            contentColor = Color.White  // Color of the text inside the button
                        )
                    ) { Text(text = "Suspend Student") }

                }
            }
        }
    }
}