package com.example.matuleclothes.presentation.CreateProject

import android.graphics.Bitmap
import android.net.Uri

sealed class CreateProjectEvent {
    data class EnteredType(val value: String): CreateProjectEvent()
    data class EnteredName(val value: String): CreateProjectEvent()
    data class EnteredDateStart(val value: String): CreateProjectEvent()
    data class EnteredDateEnd(val value: String): CreateProjectEvent()
    data class EnteredToWhom(val value: String): CreateProjectEvent()
    data class EnteredDescriptionSource(val value: String): CreateProjectEvent()
    data class EnteredCategory(val value: String): CreateProjectEvent()
    data class EnteredTechnicalDrawing(val value: String): CreateProjectEvent()
    data object OnConfirm: CreateProjectEvent()
    data class LoadImageFromGallery(val value: Uri): CreateProjectEvent()
    data class LoadImageFromCamera(val value: Bitmap): CreateProjectEvent()
}