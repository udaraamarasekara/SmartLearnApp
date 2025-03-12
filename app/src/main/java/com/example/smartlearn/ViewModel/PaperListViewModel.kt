package com.example.smartlearn.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlearn.Model.Links
import com.example.smartlearn.Model.PaperData
import com.example.smartlearn.Model.PaperResponse
import com.example.smartlearn.Model.StudentAndTutorData
import com.example.smartlearn.Model.StudentAndTutorResponse
import com.example.smartlearn.Model.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaperListViewModel() : ViewModel() {
    val studentRepository: StudentRepository = StudentRepository()

    private val _result = MutableStateFlow(PaperResponse(listOf<PaperData>(), Links("")))
    val result: StateFlow<PaperResponse> = _result

    init {
        getPapers(page = "")
    }

    fun getPapers(page: String) {
        viewModelScope.launch() {
            val papers = studentRepository.getPapers(page)
            papers.links.transform()
            _result.value = papers
        }

    }

    fun downloadPaper(id: Int,context: Context): Boolean {
        var response = false
        viewModelScope.launch() {
            response = 0 < studentRepository.downloadPaper(id,context)
        }
        return response
    }


}