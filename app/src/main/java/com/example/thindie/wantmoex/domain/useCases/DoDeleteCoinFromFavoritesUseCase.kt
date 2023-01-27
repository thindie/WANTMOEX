package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import javax.inject.Inject

class DoDeleteCoinFromFavoritesUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository
) {
    suspend operator fun invoke(list: List<String>) {
        favouriteCoinsRepository.deleteFromFavoriteCoins(list)
    }
}