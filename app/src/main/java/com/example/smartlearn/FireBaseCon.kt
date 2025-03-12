package com.example.smartlearn
import android.util.Log
import com.example.smartlearn.Model.Fcm
import com.google.firebase.messaging.FirebaseMessaging

 fun getFirebaseToken() {

    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val token = task.result
            // Send this token to your Laravel API
            sendTokenToServer(token)
            Log.d("FirebaseToken", "Token: $token")
        } else {
            // Handle the error
            task.exception?.let {
                it.printStackTrace()
            }
        }
    }
}
private fun sendTokenToServer(token: String) {
    // Implement your logic to send the token to the Laravel API
    Fcm.fcm = token
}