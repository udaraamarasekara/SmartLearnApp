package com.example.smartlearn.Model

enum class Role{
    Lecturer,Student,Admin
}
data class UserData(
    var name: String,
    var role: Role,
    var token:String,
)
object UserSession {
    var userData: UserData? = null
}

data class LoginData(
    var email: String,
    var password: String,
    var fcm:String
)


data class RegistrationData(
    var name: String,
    var email: String,
    var password: String,
    var password_confirmation: String,
    var fcm:String
)

object Fcm {
    var fcm: String? = null
}