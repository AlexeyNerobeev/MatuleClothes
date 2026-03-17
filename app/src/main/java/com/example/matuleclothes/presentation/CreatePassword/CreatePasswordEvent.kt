package com.example.matuleclothes.presentation.CreatePassword

sealed class CreatePasswordEvent {
    data class EnteredPassword(val value: String): CreatePasswordEvent()
    data class EnteredRepeatPassword(val value: String): CreatePasswordEvent()
    data object FirstVisualChange: CreatePasswordEvent()
    data object SecondVisualChange: CreatePasswordEvent()
}