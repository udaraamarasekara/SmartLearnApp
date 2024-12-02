package com.example.smartlearn.Model


import android.util.Log
import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private val retrofitService = RetrofitService.getInstance()
    suspend fun login(loginData: LoginData): Boolean {
        var result: Any = false
        return withContext(Dispatchers.IO) {
            try {
                UserSession.userData =retrofitService.login(loginData)
                UserSession.userData?.let { RetrofitService.setAuthToken(it.token) }
                result =true
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                Log.d("error",e.toString())
                result=false
            }
            result
        }
    }


}