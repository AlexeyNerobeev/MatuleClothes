package com.example.matuleclothes.domain.usecase

class IsPasswordValidUseCase {
    operator fun invoke(password: String): Boolean{
        return password.length >= 9 &&
                password.any{it.isLowerCase()} &&
                password.any{it.isUpperCase()} &&
                password.any{it.isDigit()} &&
                password.any{!it.isLetterOrDigit() && !it.isWhitespace()}
    }
}