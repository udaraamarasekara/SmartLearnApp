package com.example.smartlearn.ViewModel

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlearn.Model.TutorRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UploadPaperViewModel() : ViewModel() {
    var fileUploadStatus = mutableStateOf(0)

    private val tutorRepository: TutorRepository = TutorRepository()

    fun uploadPaper(fileUri: Uri?, name: String,context: Context) {

        viewModelScope.launch {
            try {
                // Check if fileUri is null
                val inputStream = context.contentResolver.openInputStream(fileUri!!)
                    val file = File(context.cacheDir,name)
                   file.createNewFile()
                   file.outputStream().use {
                       inputStream!!.copyTo(it)
                }
                    if (!file.exists()) {
                        fileUploadStatus.value = -1 // Indicate error for file not found
                        return@launch
                    }
                    val mediaType = "application/octet-stream".toMediaTypeOrNull() // Change as needed

                    val requestFile = file.asRequestBody(mediaType)
                    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)



                // Resolve the Uri to a File


                // Create RequestBody for the file

                // Call the repository to upload the paper
                val response = tutorRepository.uploadPaper(body, name.toRequestBody())
                if (response == 1) {
                    fileUploadStatus.value = 1 // Success
                } else {
                    fileUploadStatus.value = -1 // Indicate error
                }
            } catch (e: Exception) {
                fileUploadStatus.value = -1 // Indicate error
                e.printStackTrace()
            }
        }
    }


}