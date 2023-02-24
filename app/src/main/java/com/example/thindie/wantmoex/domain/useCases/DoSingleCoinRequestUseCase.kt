package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DoSingleCoinRequestUseCase @Inject constructor(
    private val repository: CryptoCoinRepository,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) {
    operator fun invoke(fromSymbol: String): Flow<Results<Coin>> {
        return repository.observeCoin(fromSymbol).flowOn(IO)
    }

    suspend fun getCoin(fromSymbol: String): Results<Coin> {
        return repository.getCoin(fromSymbol)
    }
}