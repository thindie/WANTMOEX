package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteCoinsUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository
) {
    suspend operator fun invoke(): Flow<List<Coin>> {
        return favouriteCoinsRepository.getAllFavoriteCoins()
    }
}