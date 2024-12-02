package com.example.smartlearn.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartlearn.Model.StudentData

@Composable
fun StudentCard(student:StudentData) {
    Card(
        modifier = Modifier
            .fillMaxWidth().fillMaxSize().defaultMinSize(minHeight =140.dp)
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

            }
        }
    }
}