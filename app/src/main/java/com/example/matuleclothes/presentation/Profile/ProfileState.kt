package com.example.matuleclothes.presentation.Profile

import android.graphics.Bitmap

data class ProfileState(
    val name: String = "",
    val phone: String = "",
    val notifications: Boolean = true,
    val showPdf: Boolean = false,
    val pdfBitmap: Bitmap? = null
)