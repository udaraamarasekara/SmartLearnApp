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
                Log.d("",e.message.toString())
                // Handle network errors
                if(e.message.toString()=="HTTP 401 Unauthorized")
                {
                    ErrorSession.error="Invalid credentials"
                }
                else if(e.message.toString()=="HTTP 403 Forbidden")
                {
                    ErrorSession.error="User not approved"
                }
                else
                {
                   ErrorSession.error="Something went wrong"
                }
                result=false
            }
            result
        }
    }
   suspend fun logout() {
       retrofitService.logout()
   }

}