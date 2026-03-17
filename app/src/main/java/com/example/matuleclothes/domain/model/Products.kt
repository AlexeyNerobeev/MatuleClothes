package com.example.matuleclothes.domain.model

data class Products(
    val id: String = "",
    val name: String = "",
    val price: Int = 0,
    val type: String = "",
    val inCart: Boolean = false
)
