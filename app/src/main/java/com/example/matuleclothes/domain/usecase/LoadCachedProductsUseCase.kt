package com.example.matuleclothes.domain.usecase

import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.repository.ProductsRepository

class LoadCachedProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(): List<Products>{
        return productsRepository.getProducts()
    }
}