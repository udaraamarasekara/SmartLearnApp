package com.example.smartlearn.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.Fcm
import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.Role
import com.example.smartlearn.Model.UserRepository
import com.example.smartlearn.Model.UserSession
import com.example.smartlearn.getFirebaseToken
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val userRepository: UserRepository= UserRepository()
    var result = mutableStateOf(false)

    fun login(loginData: LoginData,navController: NavController){
        viewModelScope.launch(){
            getFirebaseToken()
            loginData.fcm=Fcm.fcm.toString()
            result.value = !userRepository.login(loginData)
            if(result.value==false) {
                UserSession.userData?.let {
                    when(it.role){
                        Role.Student ->navController.navigate("StudentDashboard"){ popUpTo("StudentDashboard")  {inclusive = true} }
                        Role.Lecturer ->navController.navigate("TutorDashboard"){ popUpTo("TutorDashboard")  {inclusive = true} }
                        Role.Admin ->navController.navigate("AdminDashboard"){ popUpTo("AdminDashboard")  {inclusive = true} }
                    }
                }
            }else
            {
                result.value=true

            }
        }
    }

}