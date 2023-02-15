package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.Result
import com.example.thindie.wantmoex.domain.entities.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoNewsRepositoryImpl @Inject constructor(private val cryptoCoinsApiService: CryptoCoinsApiService) :
    CryptoNewsRepository {
    override fun observeNews(): Flow<Result<List<News>>> {
        TODO("Not yet implemented")
    }

    override fun observeNews(selectedTags: List<String>): Flow<Result<List<News>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNews(selectedTags: List<String>): Result<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNews(): Result<List<News>> {
        TODO("Not yet implemented")
    }

}