package com.example.smartlearn.Model

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.example.smartlearn.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class StudentRepository {
    private val retrofitService = RetrofitService.getInstance()
    public var error: String = ""

    suspend fun getPapers(page:String): PaperResponse {
        var result: PaperResponse =PaperResponse(emptyList(),Links("",""))
        return withContext(Dispatchers.IO) {
            try {
                result =retrofitService.getPapers(page)
                // Make the network call
            } catch (e: Exception) {
                // Handle network errors
                error = "Network error"
            }
            result
        }
    }
    suspend fun downloadPaper(id: Int,context:Context): Int {
        var result=0
        return withContext(Dispatchers.IO) {
            try {
                var resp= DownloadManager.Request(Uri.parse("http://10.0.2.2:8000/api/paper/$id"))
                resp.addRequestHeader("Authorization", "Bearer ${UserSession.userData?.token}")
                resp.setMimeType("application/pdf")
                resp.setTitle("Paper $id")
                resp.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                resp.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "$id.pdf");
                val downloadManager =  context.getSystemService(DownloadManager::class.java) as DownloadManager
                val rsp= downloadManager.enqueue(resp)
                rsp
            } catch (e: Exception) {
                // Handle network errors
                Log.d("downloadPaper",e.toString())

                result=-1
            }
            result
        }
    }
    suspend fun register(registrationData: RegistrationAndProfileData): Boolean {
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

