package com.example.smartlearn.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.Fcm
import com.example.smartlearn.Model.RegistrationAndProfileData
import com.example.smartlearn.Model.StudentRepository
import com.example.smartlearn.Model.WelcomePageMsg
import com.example.smartlearn.getFirebaseToken
import kotlinx.coroutines.launch

class StudentRegistrationViewModel: ViewModel() {
    val studentRepository: StudentRepository= StudentRepository()
    var result = mutableStateOf(false)

    fun register(registerData: RegistrationAndProfileData, navController: NavController){
        viewModelScope.launch(){
            getFirebaseToken()
            registerData.fcm=Fcm.fcm.toString()
            result.value = !studentRepository.register(registerData)
            if(result.value==false) {
                WelcomePageMsg.msg ="Registered for admin approval"
                navController.navigate("WelcomePage")
            }else
            {
                result.value=true
            }
        }
    }

}