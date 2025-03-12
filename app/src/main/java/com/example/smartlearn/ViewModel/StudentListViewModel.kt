package com.example.smartlearn.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlearn.Model.Links
import com.example.smartlearn.Model.StudentAndTutorData
import com.example.smartlearn.Model.AdminRepository
import com.example.smartlearn.Model.StudentAndTutorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentListViewModel() : ViewModel() {
    val adminRepository: AdminRepository = AdminRepository()

    private val _result = MutableStateFlow(StudentAndTutorResponse(listOf<StudentAndTutorData>(), Links("")))
    val result: StateFlow<StudentAndTutorResponse> = _result

    init {
        getStudents(page = "")
    }

    fun getStudents(page: String) {
        viewModelScope.launch() {
            val students = adminRepository.getStudents(page)
            students.links.transform()
            _result.value = students
        }

    }

    fun deleteStudent(id: Int): Boolean {
        var response = false
        viewModelScope.launch() {
            response = adminRepository.deleteMember(id)
            _result.value = _result.value.copy(
                data = _result.value.data.filter { it.id != id } // Filter out the deleted student
            )

        }
        return response
    }
    fun approveStudent(id: Int): Boolean {
        var response = false
        viewModelScope.launch() {
            response = adminRepository.approveMember(id)
        }
      return response
    }
}