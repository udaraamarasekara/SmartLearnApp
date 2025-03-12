package com.example.smartlearn


import com.example.smartlearn.Model.LoginData
import com.example.smartlearn.Model.PaperResponse
import com.example.smartlearn.Model.RegistrationAndProfileData
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
import retrofit2.CallAdapter
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface RetrofitService {

    @POST("logout")
    suspend fun logout(): Int

    @POST("login")
    suspend fun login(@Body loginData: LoginData): UserData

    @POST("register")
    suspend fun register(@Body registrationData: RegistrationAndProfileData): UserData

    @GET("students")
    suspend fun getStudents(@Query("page") page: String): StudentAndTutorResponse

    @POST("tutor")
    suspend fun updateProfile(@Body registrationData: RegistrationAndProfileData): Int

    @GET("tutors")
    suspend fun getTutors(@Query("page") page: String): StudentAndTutorResponse


    @GET("papers")
    suspend fun getPapers(@Query("page") page: String): PaperResponse

    @POST("registerTutor")
    suspend fun registerTutor(@Body registrationData: RegistrationAndProfileData): Int

    @DELETE("member/{id}")
    suspend fun deleteMember(@Path("id") id: String): Boolean

    @PUT("member/{id}")
    suspend fun approveMember(@Path("id") id: String): Boolean

    @Multipart
    @POST("paper")
    suspend fun uploadPaper(
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody

    ): Int


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