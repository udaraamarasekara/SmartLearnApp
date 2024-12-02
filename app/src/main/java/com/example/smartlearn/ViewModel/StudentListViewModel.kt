package com.example.smartlearn.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlearn.Model.Links
import com.example.smartlearn.Model.StudentData
import com.example.smartlearn.Model.AdminRepository
import com.example.smartlearn.Model.StudentResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentListViewModel() : ViewModel() {
    val adminRepository: AdminRepository = AdminRepository()

    private val _result = MutableStateFlow(StudentResponse(listOf<StudentData>(),Links("")))
    val result: StateFlow<StudentResponse> = _result
    init {
        getStudents(page = "")
    }
    fun getStudents(page:String)
    {
        viewModelScope.launch(){
            val students = adminRepository.getStudents(page)
            students.links.transform()
            _result.value =students
        }

    }
}