package com.example.smartlearn.Model

data class PaperData (
    var title: String,
    var id: Int
)

data class PaperResponse(
    val data: List<PaperData>,
    val links: Links,
)