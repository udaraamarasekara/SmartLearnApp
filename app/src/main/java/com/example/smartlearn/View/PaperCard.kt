package com.example.smartlearn.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartlearn.Model.PaperData
import com.example.smartlearn.Model.StudentAndTutorData

@Composable
fun PaperCard(paper: PaperData, onDownload: (id: Int) -> Unit) {
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
                Text(text = "Name: ${paper.title}", style = MaterialTheme.typography.labelLarge)
                Row {
                    Button(
                        onClick = {
                            onDownload(paper.id)
                        },
                        modifier = Modifier.padding(
                            5.dp,
                            5.dp,
                            0.dp,
                            0.dp
                        )
                    ) { Text(text = "Download Paper") }
                }
            }
        }
    }
}