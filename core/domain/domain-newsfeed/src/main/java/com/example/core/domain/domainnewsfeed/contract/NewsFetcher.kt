package com.example.core.domain.domainnewsfeed.contract

import com.example.core.domain.domainnewsfeed.entity.News

interface NewsFetcher {
    suspend operator fun invoke(): List<News>
}
