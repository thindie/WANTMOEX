package com.example.core.domain.domainnewsfeed.repository

import com.example.core.domain.domainnewsfeed.entity.News

interface NewsFeedRepository {
    suspend fun getNews(): List<News>
}
