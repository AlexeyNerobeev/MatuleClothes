package com.example.matuleclothes.presentation.Main

sealed class MainEvent {
    data class EnteredSearch(val value: String): MainEvent()
    data object FirstButtonClick: MainEvent()
    data object SecondButtonClick: MainEvent()
    data object ThirdButtonClick: MainEvent()
}