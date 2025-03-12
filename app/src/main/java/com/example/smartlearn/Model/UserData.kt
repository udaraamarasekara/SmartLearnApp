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
object ErrorSession {
    var error: String? = null
}

object WelcomePageMsg{
    var msg: String? = null
}

data class RegistrationAndProfileData(
    var name: String,
    var email: String,
    var password: String,
    var password_confirmation: String,
    var fcm:String
)

object Fcm {
    var fcm: String? = null
}

data class Links(
    var first: String,
    var last: String? = null,
    var prev: String? = null,
    var next: String? = null
)
{
    fun transform() {
        first = first.substringAfter("page=")
        last = last?.substringAfter("page=")
        prev = prev?.substringAfter("page=")
        next = next?.substringAfter("page=")
    }
}