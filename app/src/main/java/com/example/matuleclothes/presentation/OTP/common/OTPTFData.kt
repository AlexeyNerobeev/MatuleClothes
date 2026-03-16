package com.example.matuleclothes.presentation.OTP.common

import androidx.compose.ui.Modifier

data class OTPTFData(
    val modifier: Modifier,
    val value: String,
    val onValueChange: (String) -> Unit
)
