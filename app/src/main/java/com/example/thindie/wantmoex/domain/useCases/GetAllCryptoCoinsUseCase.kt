package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCryptoCoinsUseCase @Inject constructor(private val repository: CryptoCoinRepository) {
    suspend operator fun invoke(): Flow<List<Coin>> {
        return repository.getAll().flowOn(Dispatchers.IO)
    }
}