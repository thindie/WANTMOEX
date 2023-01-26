package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow

interface CryptoNewsRepository {
    suspend fun getNews(): Flow<List<News>>
}