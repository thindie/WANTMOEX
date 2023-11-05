package com.example.thindie.domain

interface Repository<T> {
    suspend fun request(request: SimpleRequest)
    fun observeEvent(): Flow<Event<T>>

}
