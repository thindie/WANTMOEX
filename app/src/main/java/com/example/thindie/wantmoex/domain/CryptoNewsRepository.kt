package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow

interface CryptoNewsRepository {
    fun observeNews(): Flow<Result<List<News>>>
    suspend fun getNews(selectedTags: List<String>): Result<List<News>>
    fun observeNews(selectedTags: List<String>): Flow<Result<List<News>>>
    suspend fun getNews(): Result<List<News>>
}