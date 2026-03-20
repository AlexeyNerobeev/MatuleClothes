package com.example.matuleclothes.di

import android.content.Context
import androidx.room.Room
import com.example.matuleclothes.data.dao.ProductsDao
import com.example.matuleclothes.data.db.AppDatabase
import com.example.matuleclothes.data.repositoryImpl.ProductsRepositoryImpl
import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.repository.ProductsRepository
import com.example.matuleclothes.domain.usecase.IsEmailValidUseCase
import com.example.matuleclothes.domain.usecase.IsPasswordValidUseCase
import com.example.matuleclothes.domain.usecase.LoadCachedProductsUseCase
import com.example.matuleclothes.domain.usecase.SaveProductsUseCase
import com.example.matuleclothes.domain.usecase.SendNotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideIsEmailValidUseCase(): IsEmailValidUseCase{
        return IsEmailValidUseCase()
    }

    @Provides
    fun provideIsPasswordValidUseCase(): IsPasswordValidUseCase{
        return IsPasswordValidUseCase()
    }

    @Provides
    fun provideSendNotificationUseCase(@ApplicationContext context: Context): SendNotificationUseCase{
        return SendNotificationUseCase(context)
    }

    @Provides
    fun provideProductsRepository(productsDao: ProductsDao): ProductsRepository{
        return ProductsRepositoryImpl(productsDao)
    }

    @Provides
    fun provideSaveProductsUseCase(productsRepository: ProductsRepository): SaveProductsUseCase{
        return SaveProductsUseCase(productsRepository)
    }

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "matule_db"
        ).build()
    }

    @Provides
    fun provideProductsDao(appDatabase: AppDatabase): ProductsDao{
        return appDatabase.productsDao()
    }

    @Provides
    fun provideLoadCachedProductsUseCase(productsRepository: ProductsRepository): LoadCachedProductsUseCase{
        return LoadCachedProductsUseCase(productsRepository)
    }
}