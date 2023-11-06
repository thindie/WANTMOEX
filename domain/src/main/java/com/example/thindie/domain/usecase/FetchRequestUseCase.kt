package com.example.thindie.domain.usecase

import com.example.thindie.domain.Repository
import com.example.thindie.domain.SimpleRequest
import com.example.thindie.domain.entities.Coin
import javax.inject.Inject

class FetchRequestUseCase @Inject constructor(private val repository: Repository<List<Coin>>) {
    suspend operator fun invoke() {
        repository.request(SimpleRequest.Refresh)
    }
}
