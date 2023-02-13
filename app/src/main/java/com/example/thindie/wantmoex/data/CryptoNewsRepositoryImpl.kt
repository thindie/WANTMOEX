package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.dto.parseRawNewsDTOtoNewsDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoNewsRepositoryImpl @Inject constructor(private val cryptoCoinsApiService: CryptoCoinsApiService) :
    CryptoNewsRepository {
    override suspend fun getNews(): Flow<List<News>?> {
        return flow {
            emit(
                parseRawNewsDTOtoNewsDTO(cryptoCoinsApiService.getLastestNews()).map {
                    it.map()
                }
            )
         }
    }
}