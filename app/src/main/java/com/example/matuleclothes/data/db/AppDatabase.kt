package com.example.matuleclothes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matuleclothes.data.dao.ProductsDao
import com.example.matuleclothes.data.entities.ProductsEntity

@Database(entities = [ProductsEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}