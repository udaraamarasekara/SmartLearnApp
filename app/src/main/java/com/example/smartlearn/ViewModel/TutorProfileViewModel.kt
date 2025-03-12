package com.example.smartlearn.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.RegistrationAndProfileData
import com.example.smartlearn.Model.TutorRepository

import kotlinx.coroutines.launch

class TutorProfileViewModel: ViewModel() {
    val tutorRepository: TutorRepository = TutorRepository()
    var result = mutableStateOf(false)

    fun updateProfile(profileData: RegistrationAndProfileData, navController: NavController) {
        viewModelScope.launch() {
            result.value = !tutorRepository.updateProfile(profileData)
            if (result.value == false) {
                navController.navigate("tutorDashboard")
            } else {
                result.value = true
            }
        }

    }
}