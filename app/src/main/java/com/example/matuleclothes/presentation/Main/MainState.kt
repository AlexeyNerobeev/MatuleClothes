package com.example.matuleclothes.presentation.Main

import com.example.matuleclothes.domain.model.Products

data class MainState(
    val search: String = "",
    val productsList: List<Products> = listOf(),
    val firstActive: Boolean = true,
    val secondActive: Boolean = false,
    val thirdActive: Boolean = false,
    val catalog: List<Products> = listOf()
)