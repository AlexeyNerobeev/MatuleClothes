package com.example.matuleclothes.presentation.CreateProfile

data class CreateProfileState(
    val name: String = "",
    val patronymic: String = "",
    val surname: String = "",
    val birthday: String = "",
    val gender: String = "",
    val telegram: String = "",
    val isProfileCreate: Boolean = false
)