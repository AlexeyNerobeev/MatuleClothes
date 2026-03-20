package com.example.matuleclothes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matuleclothes.data.entities.ProductsEntity

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(list: List<ProductsEntity>)
}