package com.example.matuleclothes.presentation.CreatePassword.common

import androidx.compose.ui.Modifier

data class PasswordTFData(
    val modifier: Modifier,
    val value: String,
    val onValueChange: (String) -> Unit,
    val title: String?,
    val placeholder: String
)