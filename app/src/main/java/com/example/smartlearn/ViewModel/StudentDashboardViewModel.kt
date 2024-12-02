package com.example.smartlearn.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlearn.Model.StudentRepository
import kotlinx.coroutines.launch

class StudentDashboardViewModel: ViewModel() {
    val studentRepository: StudentRepository= StudentRepository()
    var result = 0
    fun getStudentPapers()
    {
        viewModelScope.launch(){
            result = studentRepository.getStudentPapers()

        }

    }

}