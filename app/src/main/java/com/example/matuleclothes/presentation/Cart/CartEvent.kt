package com.example.matuleclothes.presentation.Cart

sealed class CartEvent {
    data object OnPlus: CartEvent()
    data object OnMinus: CartEvent()
}