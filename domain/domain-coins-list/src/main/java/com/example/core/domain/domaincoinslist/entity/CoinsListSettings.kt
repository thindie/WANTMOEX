package com.example.core.domain.domaincoinslist.entity

interface CoinsListSettings {
    val capacity: Int
    val sortedBy: String
    val isFavorites: Boolean
    val isAsc: Boolean
}
