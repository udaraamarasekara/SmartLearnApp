package com.example.smartlearn

import TutorProfile
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartlearn.View.LoginPage
import com.example.smartlearn.ui.theme.SmartLearnTheme
import androidx.navigation.compose.rememberNavController
import com.example.smartlearn.Model.UserRepository
import com.example.smartlearn.View.AdminDashboard
import com.example.smartlearn.View.PaperList
import com.example.smartlearn.View.RegistrationPage

import com.example.smartlearn.View.StudentList
import com.example.smartlearn.View.TutorDashboard
import com.example.smartlearn.View.TutorList
import com.example.smartlearn.View.TutorRegistrationPage
import com.example.smartlearn.View.UploadPaperPage
import com.example.smartlearn.View.WelcomePage

import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        requestPermissions()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            SmartLearnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(navController = navController, startDestination = "WelcomePage") {
                        composable(route = "WelcomePage") {
                            WelcomePage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "Login") {
                            LoginPage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "Registration") {
                            RegistrationPage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "StudentDashboard") {
                            PaperList(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "AdminDashboard") {
                            AdminDashboard(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "TutorRegistration") {
                            TutorRegistrationPage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "StudentList") {
                            StudentList(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "TutorList") {
                            TutorList(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "TutorProfile") {
                            TutorProfile(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "TutorDashboard") {
                            TutorDashboard(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "UploadPaper") {
                            UploadPaperPage(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }

                    }

                }
            }
        }
    }
    val userRepository: UserRepository= UserRepository()

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {

            userRepository.logout()
        }
    }
    fun requestPermissions(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU)
        {
            val hasPermission = ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)==PackageManager.PERMISSION_GRANTED
            if (!hasPermission)
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)
            }

        }

        if (ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }

}
