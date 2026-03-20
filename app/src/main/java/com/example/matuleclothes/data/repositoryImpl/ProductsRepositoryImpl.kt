package com.example.matuleclothes.data.repositoryImpl

import com.example.matuleclothes.data.dao.ProductsDao
import com.example.matuleclothes.data.mappers.toDao
import com.example.matuleclothes.data.mappers.toModel
import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsDao: ProductsDao
): ProductsRepository {
    override suspend fun saveProducts(products: List<Products>) {
        productsDao.saveProducts(products.map { it.toDao() })
    }

    override suspend fun getProducts(): List<Products> {
        return productsDao.getProducts().map { it.toModel() }
    }
}