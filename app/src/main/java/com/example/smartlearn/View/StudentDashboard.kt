package com.example.smartlearn.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smartlearn.Model.UserSession.userData
import com.example.smartlearn.ViewModel.StudentDashboardViewModel
import com.example.smartlearn.ui.theme.lightBlue

@Composable
fun StudentDashboard(modifier: Modifier=Modifier,viewModel: StudentDashboardViewModel,navController: NavController) {
    var notificationCount = remember {
        mutableStateOf(viewModel.result)
    }

    Column(
        modifier = modifier.fillMaxSize().background(color = lightBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (notificationCount.value != 0) {
            Button(onClick ={
                viewModel.getStudentPapers()

            }
                ,modifier= Modifier.padding(0.dp,0.dp,0.dp,0.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Background color of the button
                    contentColor = Color.White  // Color of the text inside the button
                ) ) {
                Text(text = "You have ${notificationCount.value} notifications",modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp).background(color = Color.Red ,shape = RoundedCornerShape(8.dp)).padding(30.dp,10.dp,20.dp,10.dp), color = Color.White)

            }
        }
        Text(text= userData?.name ?: "")
    }
}