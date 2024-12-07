package com.example.smartlearn

import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.RegistrationData
import com.example.smartlearn.Model.StudentAndTutorResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import com.example.smartlearn.Model.UserData
import okhttp3.MultipartBody
import retrofit2.http.Body
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @POST("login")
    suspend fun login(@Body loginData: LoginData): UserData

    @POST("register")
    suspend fun register(@Body registrationData: RegistrationData): UserData

    @GET("students")
    suspend fun getStudents(@Query("page") page: String): StudentAndTutorResponse

    @GET("tutors")
    suspend fun getTutors(@Query("page") page: String): StudentAndTutorResponse

    @GET("studentPapers")
    suspend fun getStudentPapers(): Int

    @POST("registerTutor")
    suspend fun registerTutor(@Body registrationData: RegistrationData): Int

    @DELETE("member/{id}")
    suspend fun deleteMember(@Path("id") id: String): Boolean

    @Multipart
    @POST("paper")
    suspend fun uploadPaper(
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody

    ): ResponseBody


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