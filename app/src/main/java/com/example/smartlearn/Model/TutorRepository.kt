package com.example.smartlearn.Model

import android.util.Log
import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class TutorRepository {
    private val retrofitService = RetrofitService.getInstance()
    suspend fun uploadPaper(paper: MultipartBody.Part, name:RequestBody): Boolean {
        var result=false
        return withContext(Dispatchers.IO) {
            try {
                  result = retrofitService.uploadPaper(paper,name)==1
//                Log.d("Paper uploaded", res.toString())
//
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                Log.d("Error uploading paper", e.toString())
                result=false
            }
            result
        }
    }

    suspend fun updateProfile(profileData: RegistrationAndProfileData): Boolean {
        var result: Any = false
        return withContext(Dispatchers.IO) {
            try {
                retrofitService.updateProfile(profileData)
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