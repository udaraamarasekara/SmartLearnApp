package com.example.smartlearn.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlearn.Model.UserRepository
import kotlinx.coroutines.launch
import com.example.smartlearn.ui.theme.lightBlue

@Composable
fun TutorDashboard(modifier: Modifier=Modifier,navController: NavController) {
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = modifier.fillMaxSize().background(color = lightBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Staff Dashboard",
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
            fontSize = 30.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Button(onClick = {
            navController.navigate("tutorProfile")

        },
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red, // Background color of the button
                contentColor = Color.White  // Color of the text inside the button
            )) { Text(text = "Profile") }

        Button(onClick = {
            navController.navigate("UploadPaper")
        },
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green, // Background color of the button
                contentColor = Color.White  // Color of the text inside the button
            )) { Text(text = "Upload Paper") }

        Button(onClick = {

            coroutineScope.launch() {
                val userRepository= UserRepository()
                userRepository.logout()
            }
            navController.navigate("WelcomePage")

        },
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow, // Background color of the button
                contentColor = Color.Red  // Color of the text inside the button
            )) { Text(text = "Logout") }

    }
}