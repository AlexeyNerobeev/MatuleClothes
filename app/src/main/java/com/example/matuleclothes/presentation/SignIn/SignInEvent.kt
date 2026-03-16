package com.example.matuleclothes.presentation.SignIn

sealed class SignInEvent {
    data class EnteredEmail(val value: String): SignInEvent()
    data class EnteredPassword(val value: String): SignInEvent()
    data object VisualChange: SignInEvent()
    data object IsFieldsEmpty: SignInEvent()
    data object Login: SignInEvent()
}