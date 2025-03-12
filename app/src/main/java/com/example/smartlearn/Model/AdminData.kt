package com.example.smartlearn.Model


data class StudentAndTutorData (
    val id: Int,
    val name: String,
    val email: String,
    val is_approved: Int
)



data class StudentAndTutorResponse(
    val data: List<StudentAndTutorData>,
    val links: Links,
)