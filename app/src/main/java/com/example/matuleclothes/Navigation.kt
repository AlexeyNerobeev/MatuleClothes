package com.example.matuleclothes

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    data object Splash: Navigation()

    @Serializable
    data object SingIn: Navigation()
}