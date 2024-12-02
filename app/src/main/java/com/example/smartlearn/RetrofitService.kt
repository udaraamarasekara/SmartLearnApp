package com.example.smartlearn

import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.RegistrationData
import com.example.smartlearn.Model.StudentResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import com.example.smartlearn.Model.UserData
import retrofit2.http.Body
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @POST("login")
    suspend fun login(@Body loginData: LoginData): UserData

    @POST("register")
    suspend fun register(@Body registrationData: RegistrationData): UserData

    @GET("getStudents")
    suspend fun getStudents(@Query("page") page: String): StudentResponse

    @GET("getStudentPapers")
    suspend fun getStudentPapers(): Int

    @POST("registerTutor")
    suspend fun registerTutor(@Body registrationData: RegistrationData): UserData


    companion object {
        @Volatile
        private var retrofitService: RetrofitService? = null
        private var authToken: String? = null
        fun setAuthToken(token: String) {
            authToken = token
            // Reset the instance so it uses the updated token
            retrofitService = null
        }

        // Singleton instance with Bearer token support
        fun getInstance(): RetrofitService {
            return retrofitService ?: synchronized(this) {
                val client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val requestBuilder = chain.request().newBuilder()
                        authToken?.let {
                            requestBuilder.header("Authorization", "Bearer $it")
                        }
                        chain.proceed(requestBuilder.build())
                    }
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
                retrofitService!!
            }
        }
    }
}