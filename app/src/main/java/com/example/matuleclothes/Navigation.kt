package com.example.matuleclothes

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    data object Splash: Navigation()

    @Serializable
    data object SingIn: Navigation()

    @Serializable
    data object Main: Navigation()

    @Serializable
    data object CreateProfile: Navigation()

    @Serializable
    data object OTP: Navigation()

    @Serializable
    data object CreatePassword: Navigation()

    @Serializable
    data object Catalog: Navigation()

    @Serializable
    data object Cart: Navigation()

    @Serializable
    data object Projects: Navigation()
}