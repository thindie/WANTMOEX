package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.EntityRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import javax.inject.Inject

class DoSingleCoinRequestUseCase @Inject constructor(private val repository: EntityRepository) {
    suspend operator fun invoke(fromSymbol: String) :  Coin  {
        return repository.getSingle(fromSymbol)
    }
}