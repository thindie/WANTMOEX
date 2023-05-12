package com.example.favorites.di

import com.example.domainfavorites.repository.FavoritesRepository
import com.example.favorites.repository.FavoritesManagerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FavoritesManagerModule {
    @Binds
    fun bindFavoritesManager(impl: FavoritesManagerRepositoryImpl): FavoritesRepository
}
