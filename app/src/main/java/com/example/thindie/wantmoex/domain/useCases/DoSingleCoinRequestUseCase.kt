package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Result
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoSingleCoinRequestUseCase @Inject constructor(private val repository: CryptoCoinRepository) {
    operator fun invoke(fromSymbol: String): Flow<Result<Coin>> {
        return repository.observeCoin(fromSymbol)
    }

    suspend fun getCoin(fromSymbol: String): Result<Coin> {
        return repository.getCoin(fromSymbol)
    }
}