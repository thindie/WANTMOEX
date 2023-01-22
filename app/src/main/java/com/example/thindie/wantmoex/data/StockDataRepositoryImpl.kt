package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.retrofit.StockApiService
import com.example.thindie.wantmoex.domain.EntityRepository
import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class StockDataRepositoryImpl @Inject constructor(
    private val stockApiService: StockApiService

) : EntityRepository {
    override suspend fun getAll(): Flow<List<Share>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingle(share: Share): Flow<List<Share>> {
        TODO("Not yet implemented")
    }

}