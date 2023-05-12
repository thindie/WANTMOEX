package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.News

interface CryptoNewsRepository {
    suspend fun getNews(selectedTags: List<String>): Results<List<News>>
}
