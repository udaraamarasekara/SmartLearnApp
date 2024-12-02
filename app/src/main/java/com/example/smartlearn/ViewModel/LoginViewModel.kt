package com.example.smartlearn.ViewModel

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.example.smartlearn.Model.Fcm
import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.Role
import com.example.smartlearn.Model.UserRepository
import com.example.smartlearn.Model.UserSession
import com.example.smartlearn.View.LoginPage
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
                        Role.Lecturer ->navController.navigate("LecturerDashboard"){ popUpTo("LecturerDashboard")  {inclusive = true} }
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