package com.example.matuleclothes.presentation.CreateProfile

sealed class CreateProfileEvent {
    data class EnteredName(val value: String): CreateProfileEvent()
    data class EnteredPatronymic(val value: String): CreateProfileEvent()
    data class EnteredSurname(val value: String): CreateProfileEvent()
    data class EnteredBirthday(val value: String): CreateProfileEvent()
    data class EnteredTelegram(val value: String): CreateProfileEvent()
    data class SelectGender(val value: String): CreateProfileEvent()
    data object CreateProfile: CreateProfileEvent()
}