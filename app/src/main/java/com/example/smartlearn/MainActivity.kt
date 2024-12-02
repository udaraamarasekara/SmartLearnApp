package com.example.smartlearn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartlearn.View.LoginPage
import com.example.smartlearn.ViewModel.LoginViewModel
import com.example.smartlearn.ui.theme.SmartLearnTheme
import androidx.navigation.compose.rememberNavController
import com.example.smartlearn.View.AdminDashboard
import com.example.smartlearn.View.RegistrationPage

import com.example.smartlearn.View.StudentDashboard
import com.example.smartlearn.View.TutorRegistrationPage
import com.example.smartlearn.ViewModel.StudentRegistrationViewModel
import com.example.smartlearn.ViewModel.StudentDashboardViewModel
import com.example.smartlearn.ViewModel.TutorRegistrationViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            SmartLearnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(navController = navController, startDestination = "login") {
                        composable(route = "login") {
                            LoginPage(viewModel = LoginViewModel(),modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                        composable(route = "registration") {
                            RegistrationPage(viewModel = StudentRegistrationViewModel(),modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                        composable(route = "StudentDashboard"){
                            StudentDashboard(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = StudentDashboardViewModel(),
                                navController = navController,
                            )
                        }
                        composable(route = "AdminDashboard"){
                            AdminDashboard(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "TutorRegistration"){
                            TutorRegistrationPage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                viewModel = TutorRegistrationViewModel()
                            )
                        }

                    }

                }
            }
        }
    }

}
