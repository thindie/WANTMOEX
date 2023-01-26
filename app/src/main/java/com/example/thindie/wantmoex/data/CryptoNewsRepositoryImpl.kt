package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.fromNewsItselfDTOtoNews
import com.example.thindie.wantmoex.data.network.dto.parseRawNewsDTOtoNewsItselfDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoNewsRepositoryImpl @Inject constructor(private val cryptoCoinsApiService: CryptoCoinsApiService) :
    CryptoNewsRepository {
    override suspend fun getNews(): Flow<List<News>> {
        return flow {
            emit(
                parseRawNewsDTOtoNewsItselfDTO(cryptoCoinsApiService.getLastestNews()).map {
                    fromNewsItselfDTOtoNews(it)
                }
            )

        }
    }
}