package com.example.matuleclothes.domain.usecase

import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.repository.ProductsRepository

class LoadFilterCachedProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(filter: String): List<Products>{
        return productsRepository.loadFilterProducts(filter)
    }
}