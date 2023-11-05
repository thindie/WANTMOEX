package com.example.thindie.domain

sealed class SimpleRequest {
    object Refresh : SimpleRequest()

    data class Set(val order: Int) : SimpleRequest()
}
