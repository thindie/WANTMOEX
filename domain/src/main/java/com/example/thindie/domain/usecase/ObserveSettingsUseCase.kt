package com.example.thindie.domain.usecase

import com.example.thindie.domain.Repository
import com.example.thindie.domain.options.Option
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveSettingsUseCase @Inject constructor(private val repository: Repository<Option>) {
    operator fun invoke(): Flow<Option> {
        return repository
            .observeEvent()
            .map { event ->
                event.onEvent()
            }
    }
}
