package com.example.domainfavorites.usecase

import com.example.domainfavorites.contract.FavoritesManager
import com.example.domainfavorites.repository.FavoritesRepository
import javax.inject.Inject

/**
 * triggers [FavoritesRepository] to change state by ticker as id
 */
internal class ManageFavoritesUseCase @Inject constructor(private val repository: FavoritesRepository) :
    FavoritesManager {
    override suspend fun changeFavoriteState(ticker: String) {
        repository.changeFavoriteState(ticker)
    }
}
