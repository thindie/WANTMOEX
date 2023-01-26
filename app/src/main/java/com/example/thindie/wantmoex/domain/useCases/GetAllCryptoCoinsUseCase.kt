package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCryptoCoinsUseCase @Inject constructor(
    private val repository: CryptoCoinRepository,
    @DispatchersModule.IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<Coin>> {
        return repository.getAll().flowOn(ioDispatcher)
    }
}