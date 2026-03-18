package com.example.matuleclothes.presentation.Catalog

import com.example.matuleclothes.domain.model.Products

data class CatalogState(
    val search: String = "",
    val catalog: List<Products> = listOf(),
    val firstActive: Boolean = true,
    val secondActive: Boolean = false,
    val thirdActive: Boolean = false,
    val showDescription: Boolean = false,
    val description: String = "",
    val approximateCost: String = "",
    val title: String = "",
    val price: Int = 0,
    val productId: String = "",
    val goToCart: Boolean = false
)