package com.example.matuleclothes.presentation.CreateProject

import android.graphics.Bitmap

data class CreateProjectState(
    val type: String = "",
    val name: String = "",
    val dateStart: String = "",
    val dateEnd: String = "",
    val toWhom: String = "",
    val descriptionSource: String = "",
    val category: String = "",
    val technicalDrawing: Bitmap? = null,
    val isComplete: Boolean = false,
    val loadFromGallery: Boolean = false,
    val loadFromCamera: Boolean = false
)