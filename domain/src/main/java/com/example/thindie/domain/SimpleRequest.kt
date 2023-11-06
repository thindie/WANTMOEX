package com.example.thindie.domain

sealed class SimpleRequest {
    object Refresh : SimpleRequest()

    data class Set<T : Any>(val t: T) : SimpleRequest()
}
