package com.example.matuleclothes.presentation.Catalog

import com.example.matuleclothes.domain.model.Products

data class CatalogState(
    val search: String = "",
    val catalog: List<Products> = listOf(),
    val firstActive: Boolean = true,
    val secondActive: Boolean = false,
    val thirdActive: Boolean = false
)