package com.example.matuleclothes.presentation.Catalog

import com.example.matuleclothes.domain.model.Products


sealed class CatalogEvent {
    data class EnteredSearch(val value: String): CatalogEvent()
    data object FirstButtonClick: CatalogEvent()
    data object SecondButtonClick: CatalogEvent()
    data object ThirdButtonClick: CatalogEvent()
    data object ShowDescription: CatalogEvent()
    data class GetDescription(val value: String): CatalogEvent()
    data class AddToCart(val value: String): CatalogEvent()
}