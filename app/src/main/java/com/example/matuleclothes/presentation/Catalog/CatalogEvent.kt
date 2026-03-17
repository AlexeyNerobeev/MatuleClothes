package com.example.matuleclothes.presentation.Catalog


sealed class CatalogEvent {
    data class EnteredSearch(val value: String): CatalogEvent()
    data object FirstButtonClick: CatalogEvent()
    data object SecondButtonClick: CatalogEvent()
    data object ThirdButtonClick: CatalogEvent()
}