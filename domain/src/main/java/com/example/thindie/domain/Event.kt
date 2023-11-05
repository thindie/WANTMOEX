package com.example.thindie.domain

interface Event<T> {
    fun onEvent(): T
}
