package com.example.matuleclothes.di

import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.usecase.IsEmailValidUseCase
import com.example.matuleclothes.domain.usecase.IsPasswordValidUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
}