package com.example.smartlearn.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.smartlearn.ViewModel.StudentListViewModel
import androidx.compose.foundation.lazy.items // or auto-fix imports
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import kotlinx.coroutines.flow.filter

@Composable
fun StudentList(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: StudentListViewModel = remember { StudentListViewModel()}
    // Collect state from ViewModel
    val result = viewModel.result.collectAsState()
    val students = result.value.data // Ensure non-nullable list
    val listState = rememberLazyListState() // LazyColumn state
    var isNearTop = false
    var isNearBottom = false



//    var deletedStudent = remember { 0 }
    // Pagination logic
    fun deleteStudent(id:Int)
    {
        viewModel.deleteStudent(id)
    }

    fun approveStudent(id:Int)
    {
        viewModel.approveStudent(id)

    }
    val shouldLoadMore = remember {
        derivedStateOf {
            val firstVisibleItemIndex =
                listState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItemsCount = listState.layoutInfo.totalItemsCount

            // Check for near-top and near-bottom
            if(totalItemsCount!=0  ) {
                isNearTop = firstVisibleItemIndex <= 2 // Adjust the threshold as needed
                isNearBottom =
                    lastVisibleItemIndex >= totalItemsCount - 1
            }
                isNearTop || isNearBottom
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged() // Trigger only on changes
            .filter {it}
            .collect {
                if (isNearTop && result.value.links.prev.toString()!="null") {
                    Log.d("StudentList", "Near top")
                    // Load previous data if available
                    viewModel.getStudents(result.value.links.prev.toString())

                }
                else if (isNearBottom && result.value.links.next.toString()!="null") {
                    // Load next data if available
                    Log.d("StudentList", "Near bottom")

                    viewModel.getStudents(result.value.links.next.toString())
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
                StudentCard(
                    student, onDelete = { id ->
                        deleteStudent(id)
                    },
                    onApprove = { id -> approveStudent(id) })}

    }
        Button(onClick = {
            navController.navigate("AdminDashboard")
        },
            modifier = Modifier.padding(
                5.dp,
                10.dp,
                0.dp,
                0.dp
            )) { Text(text = "Back") }

    }

}