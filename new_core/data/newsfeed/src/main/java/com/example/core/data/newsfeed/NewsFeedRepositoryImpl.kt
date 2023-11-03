package com.example.core.data.newsfeed

import android.util.Log
import com.example.core.data.newsfeed.mapper.toNews
import com.example.core.domain.domainnewsfeed.entity.News
import com.example.core.domain.domainnewsfeed.repository.NewsFeedRepository
import com.example.domain.domainsettings.repository.SettingsStateProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsFeedRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val tagsObserver: SettingsStateProvider,
) : NewsFeedRepository {
    override suspend fun getNews(): List<News> {
        return try {
            networkService
                .getNews(newsTags = tagsObserver.getAppliedTags())
                .map { dto -> dto.toNews() }
        } catch (e: java.net.UnknownHostException) { //todo(
            Log.e("SERVICE_TAG", e.toString())
            emptyList()
        }
    }
}
