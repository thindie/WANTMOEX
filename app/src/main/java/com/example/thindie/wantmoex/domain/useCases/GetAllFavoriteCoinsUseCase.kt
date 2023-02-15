package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllFavoriteCoinsUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository,
    @DispatchersModule.DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<Result<List<String>>> {
        return favouriteCoinsRepository.observeAllFavoriteCoins().flowOn(defaultDispatcher)
    }
}