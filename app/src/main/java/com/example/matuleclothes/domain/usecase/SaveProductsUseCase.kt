package com.example.matuleclothes.domain.usecase

import com.example.matuleclothes.data.dao.ProductsDao
import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.repository.ProductsRepository

class SaveProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(products: List<Products>){
        productsRepository.saveProducts(products)
    }
}