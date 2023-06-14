package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.data.storage.FavouriteCoinsRepository
import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllFavoriteCoinsUseCase @Inject constructor(
    private val favouriteCoinsRepository: FavouriteCoinsRepository,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<Results<List<String>>> {
        return favouriteCoinsRepository.observeAllFavoriteCoins().flowOn(IO)
    }

    suspend fun getAllFavoriteCoins(): Results<List<String>> {
        return favouriteCoinsRepository.getAllFavoriteCoins()
    }

}