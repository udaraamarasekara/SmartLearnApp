package com.example.smartlearn.Model

import android.util.Log
import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class TutorRepository {
    private val retrofitService = RetrofitService.getInstance()
    suspend fun uploadPaper(paper: MultipartBody.Part, name:RequestBody): Int {
        var result=0
        return withContext(Dispatchers.IO) {
            try {
                 var result =retrofitService.uploadPaper(paper,name)
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                Log.d("Error uploading paper", e.toString())
                result=-1
            }
            result
        }
    }
}