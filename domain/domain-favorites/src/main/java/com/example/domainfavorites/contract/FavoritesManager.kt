package com.example.domainfavorites.contract

interface FavoritesManager {
    suspend fun changeFavoriteState(ticker: String)
}
