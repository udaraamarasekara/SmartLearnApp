package com.example.smartlearn.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smartlearn.Model.Fcm
import com.example.smartlearn.Model.RegistrationData
import com.example.smartlearn.Model.StudentRepository
import com.example.smartlearn.getFirebaseToken
import kotlinx.coroutines.launch

class StudentRegistrationViewModel: ViewModel() {
    val studentRepository: StudentRepository= StudentRepository()
    var result = mutableStateOf(false)

    fun register(registerData: RegistrationData, navController: NavController){
        viewModelScope.launch(){
            getFirebaseToken()
            registerData.fcm=Fcm.fcm.toString()
            result.value = studentRepository.register(registerData)
            if(result.value==false) {
                navController.navigate("studentDashboard")
            }else
            {
                result.value=true
            }
        }
    }

}