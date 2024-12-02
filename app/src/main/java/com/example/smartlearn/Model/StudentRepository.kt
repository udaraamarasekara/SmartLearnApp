package com.example.smartlearn.Model

import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository {
    private val retrofitService = RetrofitService.getInstance()

    suspend fun getStudentPapers(): Int {
        var result=0
        return withContext(Dispatchers.IO) {
            try {
                result =retrofitService.getStudentPapers()
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                result=-1
            }
            result
        }
    }

    suspend fun register(registrationData: RegistrationData): Boolean {
        var result: Any = false
        return withContext(Dispatchers.IO) {
            try {
                UserSession.userData =retrofitService.register(registrationData)
                UserSession.userData?.let { RetrofitService.setAuthToken(it.token) }
                result =true
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                result=false
            }
            result
        }
    }
}