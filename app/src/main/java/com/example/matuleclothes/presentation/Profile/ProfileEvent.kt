package com.example.matuleclothes.presentation.Profile

import android.content.Context

sealed class ProfileEvent {
    data object OnNotificationsChange: ProfileEvent()
    data object OnExit: ProfileEvent()
    data class OpenPdf(val context: Context, val pageIndex: Int, val fileName: String): ProfileEvent()
    data object ClosePdf: ProfileEvent()
}