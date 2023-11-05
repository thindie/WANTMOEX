package com.example.thindie.domain

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun request(request: SimpleRequest)
    fun observeEvent(): Flow<Event<T>>
}
