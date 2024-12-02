package com.example.smartlearn.Model

import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdminRepository() {
    private val retrofitService = RetrofitService.getInstance()
    public var error: String = ""
    suspend fun getStudents(page:String):StudentResponse {
        var result: StudentResponse =StudentResponse(emptyList(),Links("",""))
        return withContext(Dispatchers.IO) {
            try {
                result =retrofitService.getStudents(page)
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                error = "Network error"
            }
            result
        }
    }

    suspend fun register(registrationData: RegistrationData): Boolean {
        var result: Any = false
        return withContext(Dispatchers.IO) {
            try {
                UserSession.userData =retrofitService.registerTutor(registrationData)
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