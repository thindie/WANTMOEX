package com.example.core.domain.domaincoin.usecase

import com.example.core.domain.domaincoin.contract.CoinFetcher
import com.example.core.domain.domaincoin.entity.Coin
import com.example.core.domain.domaincoin.repository.CoinRepository
import javax.inject.Inject

internal class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
) : CoinFetcher {
    override suspend operator fun invoke(): Coin {
        return repository.getCoin()
    }
}
