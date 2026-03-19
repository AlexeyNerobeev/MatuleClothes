package com.example.matuleclothes.presentation.Profile

sealed class ProfileEvent {
    data object OnNotificationsChange: ProfileEvent()
    data object OnExit: ProfileEvent()
}