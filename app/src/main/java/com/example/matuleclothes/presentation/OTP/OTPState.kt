package com.example.matuleclothes.presentation.OTP

data class OTPState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val thirdNumber: String = "",
    val fourthNumber: String = "",
    val isAllFieldFilled: Boolean = false
)