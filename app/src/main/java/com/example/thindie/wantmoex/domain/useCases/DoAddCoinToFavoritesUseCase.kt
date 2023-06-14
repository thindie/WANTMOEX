package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.data.storage.FavouriteCoinsRepository
import javax.inject.Inject

class DoAddCoinToFavoritesUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository,
) {
    suspend operator fun invoke(id: String) {
        favouriteCoinsRepository.addToFavoriteCoins(id)
    }
}