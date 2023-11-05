package com.example.thindie.domain

interface Repository {
    fun request(request: SimpleRequest)
    fun <T> observeEvent(): Event<T>
}
