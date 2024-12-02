package com.example.smartlearn.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.smartlearn.Model.StudentData
import com.example.smartlearn.ViewModel.StudentListViewModel
import androidx.compose.foundation.lazy.items // or auto-fix imports
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlearn.ui.theme.lightBlue
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
@Composable
fun StudentList(
    modifier: Modifier = Modifier,
    viewModel: StudentListViewModel,
    navController: NavController
) {
    // Collect state from ViewModel
    val result =viewModel.result.collectAsState()
    val students = result.value.data // Ensure non-nullable list
    val listState = rememberLazyListState() // LazyColumn state
    // Pagination logic

    if(!students.isEmpty())
    {
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo }
                .map { layoutInfo ->
                    val firstVisibleItemIndex =
                        layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val totalItemsCount = layoutInfo.totalItemsCount

                    // Check for near-top and near-bottom
                    val isNearTop = firstVisibleItemIndex <= 2 // Adjust the threshold as needed
                    val isNearBottom =
                        lastVisibleItemIndex >= totalItemsCount - 1 && totalItemsCount != 0

                    isNearTop to isNearBottom
                }
                .distinctUntilChanged() // Trigger only on changes
                .collect { (isNearTop, isNearBottom) ->
                    if (isNearTop) {
                        // Load previous data if available
                        viewModel.getStudents(result.value.links.prev.toString())

                    }
                    if (isNearBottom) {
                        // Load next data if available

                        viewModel.getStudents(result.value.links.next.toString())
                    }
                }
        }

    }
    // Popup message


    // Main UI
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = lightBlue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Student List",
            modifier = Modifier.padding(bottom = 30.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        LazyColumn(state = listState) {
            items(students) { student ->
                StudentCard(student)
            }
        }

    }
}