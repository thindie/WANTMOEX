package com.example.thindie.domain.usecase

import com.example.thindie.domain.Coin
import com.example.thindie.domain.Repository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveCoinsListUseCase @Inject constructor(private val repository: Repository<List<Coin>>) {
    operator fun invoke(): Flow<List<Coin>> {
        return repository
            .observeEvent()
            .map { event ->
                event.onEvent()
            }
    }
}
