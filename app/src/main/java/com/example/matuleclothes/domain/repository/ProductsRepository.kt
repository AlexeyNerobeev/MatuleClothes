package com.example.matuleclothes.domain.repository

import com.example.matuleclothes.domain.model.Products

interface ProductsRepository {
    suspend fun saveProducts(products: List<Products>)
    suspend fun getProducts(): List<Products>
    suspend fun loadFilterProducts(filter: String): List<Products>
}