package com.example.smartlearn.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.AdminRepository
import com.example.smartlearn.Model.RegistrationAndProfileData

import kotlinx.coroutines.launch

class TutorRegistrationViewModel: ViewModel() {
    val adminRepository: AdminRepository = AdminRepository()
    var result = mutableStateOf(false)

    fun register(registerData: RegistrationAndProfileData, navController: NavController) {
        viewModelScope.launch() {
            result.value = !adminRepository.register(registerData)
            if (result.value == false) {
                navController.navigate("TutorList")
            } else {
                result.value = true
            }
        }

    }
}