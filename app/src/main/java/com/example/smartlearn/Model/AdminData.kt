package com.example.smartlearn.Model


data class StudentData (
    val name: String,
    val email: String
)
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


data class StudentResponse(
    val data: List<StudentData>,
    val links: Links,
)