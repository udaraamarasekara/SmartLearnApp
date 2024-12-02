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

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.smartlearn.ui.theme.lightBlue

@Composable
fun AdminDashboard(modifier: Modifier=Modifier,navController: NavController) {


    Column(
        modifier = modifier.fillMaxSize().background(color = lightBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Button(onClick = {

        },
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red, // Background color of the button
                contentColor = Color.White  // Color of the text inside the button
            )) { Text(text = "Tutor List") }

        Button(onClick = {
            navController.navigate("TutorRegistration")
        },
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green, // Background color of the button
                contentColor = Color.White  // Color of the text inside the button
            )) { Text(text = "Add New Tutor") }
        Button(onClick = {
            navController.navigate("StudentList")
        },
            modifier = Modifier.padding(
                0.dp,
                5.dp,
                0.dp,
                5.dp
            ).width(300.dp)) { Text(text = "Student List") }

    }
}