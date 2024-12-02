package com.example.smartlearn.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.AdminRepository
import com.example.smartlearn.Model.Fcm
import com.example.smartlearn.Model.RegistrationData
import com.example.smartlearn.Model.StudentRepository
import com.example.smartlearn.getFirebaseToken
import kotlinx.coroutines.launch

class TutorRegistrationViewModel: ViewModel() {
    val adminRepository: AdminRepository = AdminRepository()
    var result = mutableStateOf(false)

    fun register(registerData: RegistrationData, navController: NavController) {
        viewModelScope.launch() {
            result.value = !adminRepository.register(registerData)
            if (result.value == false) {
                navController.navigate("adminDashboard")
            } else {
                result.value = true
            }
        }

    }
}