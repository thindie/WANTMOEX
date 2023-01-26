package com.example.thindie.wantmoex.domain.useCases

import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetAllActualNewsUseCase(private val cryptoNewsRepository: CryptoNewsRepository) {
   suspend operator fun invoke() : Flow<List<News>> {
        return cryptoNewsRepository.getNews().flowOn(Dispatchers.IO)
    }
}