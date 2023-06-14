package com.example.domainfavorites.di

import com.example.domainfavorites.contract.FavoritesManager
import com.example.domainfavorites.usecase.ManageFavoritesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FavoritesManagerModule {
    @Binds
    fun bindFavoritesModule(useCase: ManageFavoritesUseCase): FavoritesManager
}