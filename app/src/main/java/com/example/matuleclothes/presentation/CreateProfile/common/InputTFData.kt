package com.example.matuleclothes.presentation.CreateProfile.common

import androidx.compose.ui.Modifier

data class InputTFData(
    val modifier: Modifier,
    val value: String,
    val onValueChange: (String) -> Unit,
    val title: String?,
    val placeholder: String,
    val incorrectMessage: String?
)
