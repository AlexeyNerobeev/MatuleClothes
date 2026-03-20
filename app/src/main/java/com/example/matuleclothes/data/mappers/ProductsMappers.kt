package com.example.matuleclothes.data.mappers

import com.example.matuleclothes.data.entities.ProductsEntity
import com.example.matuleclothes.domain.model.Products

internal fun Products.toDao() = ProductsEntity(
    id = id,
    name = name,
    price = price,
    type = type,
    inCart = inCart
)

internal fun ProductsEntity.toModel() = Products(
    id = id,
    name = name,
    price = price,
    type = type,
    inCart = inCart
)