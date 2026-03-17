package com.example.matuleclothes.presentation.CreatePassword

data class CreatePasswordState(
    val password: String = "",
    val repeatPassword: String = "",
    val firstVisual: Boolean = true,
    val secondVisual: Boolean = true
)