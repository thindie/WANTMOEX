package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllActualNewsUseCase @Inject constructor(
    private val cryptoNewsRepository: CryptoNewsRepository,
    @DispatchersModule.IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<News>> {
        return cryptoNewsRepository.getNews().flowOn(ioDispatcher)
    }
}