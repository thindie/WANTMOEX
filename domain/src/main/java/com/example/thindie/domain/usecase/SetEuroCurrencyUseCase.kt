package com.example.thindie.domain.usecase

import com.example.thindie.domain.Repository
import com.example.thindie.domain.SimpleRequest
import com.example.thindie.domain.entities.Coin
import com.example.thindie.domain.options.Currency
import javax.inject.Inject

class SetEuroCurrencyUseCase @Inject constructor(private val repository: Repository<List<Coin>>) {
    suspend operator fun invoke() {
        repository.request(SimpleRequest.Set(Currency.EUR))
    }
}
