package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow

interface CryptoNewsRepository {
    fun observeNews(): Flow<Results<List<News>>>
    suspend fun getNews(selectedTags: List<String>): Results<List<News>>
    fun observeNews(selectedTags: List<String>): Flow<Results<List<News>>>
    suspend fun getNews(): Results<List<News>>
}