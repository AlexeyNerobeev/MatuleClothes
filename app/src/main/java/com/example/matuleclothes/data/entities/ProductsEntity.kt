package com.example.matuleclothes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val type: String,
    val inCart: Boolean
)