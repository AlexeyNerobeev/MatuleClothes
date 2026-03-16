package com.example.matuleclothes.presentation.SignIn

data class SignInState(
    val email: String = "",
    val password: String = "",
    val visual: Boolean = true,
    val isEmptyEmail: Boolean = false,
    val isEmptyPassword: Boolean = false,
    val isLogin: Boolean = false,
    val isRegister: Boolean = false
)
