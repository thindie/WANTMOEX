package com.example.core.domain.domainnewsfeed.usecase

import com.example.core.domain.domainnewsfeed.contract.NewsFetcher
import com.example.core.domain.domainnewsfeed.entity.News
import com.example.core.domain.domainnewsfeed.repository.NewsFeedRepository
import javax.inject.Inject

internal class GetNewsUseCase @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository,
) : NewsFetcher {
    override suspend operator fun invoke(): List<News> {
        return newsFeedRepository
            .getNews()
    }
}
