package com.example.matuleclothes.presentation.OTP

sealed class OTPEvent {
    data class EnteredFirstNumber(val value: String): OTPEvent()
    data class EnteredSecondNumber(val value: String): OTPEvent()
    data class EnteredThirdNumber(val value: String): OTPEvent()
    data class EnteredFourthNumber(val value: String): OTPEvent()
    data object IsAllFieldsFilled: OTPEvent()
}