package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllActualNewsUseCase @Inject constructor(
    private val cryptoNewsRepository: CryptoNewsRepository,
    @DispatchersModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<Results<List<News>?>> {
        return cryptoNewsRepository.observeNews().flowOn(ioDispatcher)
    }

    fun observeAllNews(list: List<String>): Flow<Results<List<News>>> {
        return cryptoNewsRepository.observeNews(list).flowOn(ioDispatcher)
    }

    suspend fun getAllNews(list: List<String>): Results<List<News>> {
        return cryptoNewsRepository.getNews(list)
    }

    suspend fun getAllNews(): Results<List<News>> {
        return cryptoNewsRepository.getNews()
    }
}