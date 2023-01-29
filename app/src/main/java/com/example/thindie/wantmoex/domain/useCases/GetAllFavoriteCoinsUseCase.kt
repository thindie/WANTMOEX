package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllFavoriteCoinsUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository,
    @DispatchersModule.DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<String>> {
        return favouriteCoinsRepository.getAllFavoriteCoins().flowOn(defaultDispatcher)
    }
}