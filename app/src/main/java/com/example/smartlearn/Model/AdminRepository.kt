package com.example.smartlearn.Model

import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdminRepository() {
    private val retrofitService = RetrofitService.getInstance()
    public var error: String = ""
    suspend fun getStudents(page:String):StudentAndTutorResponse {
        var result: StudentAndTutorResponse =StudentAndTutorResponse(emptyList(),Links("",""))
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

    suspend fun getTutors(page:String):StudentAndTutorResponse {
        var result: StudentAndTutorResponse =StudentAndTutorResponse(emptyList(),Links("",""))
        return withContext(Dispatchers.IO) {
            try {
                result =retrofitService.getTutors(page)
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                error = "Network error"
            }
            result
        }
    }

    suspend fun register(registrationData: RegistrationAndProfileData): Boolean {
        var result: Any = false
        return withContext(Dispatchers.IO) {
            try {
                retrofitService.registerTutor(registrationData)
                result =true
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                result=false
            }
            result
        }
    }

    suspend fun deleteMember(id:Int): Boolean {
        var result: Boolean = false
        return withContext(Dispatchers.IO) {
            try {
                result =retrofitService.deleteMember(id.toString())

            } catch (e: Exception) {
                // Handle network errors
                error = "Network error"
            }
            result
        }
    }

    suspend fun approveMember(id:Int): Boolean {
        var result: Boolean = false
        return withContext(Dispatchers.IO){
            try {
                result =retrofitService.approveMember(id.toString())
        }catch (e: Exception) {
            // Handle network errors
            error = "Network error"
        }
        result

            }
        }
    }