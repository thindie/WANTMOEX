package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.RemoteDataSourceImpl
import com.example.thindie.wantmoex.domain.EntityRepository
import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class StockDataRepositoryImpl @Inject constructor(
    private val impl: RemoteDataSourceImpl
) : EntityRepository {
    override suspend fun getAll(): Flow<List<Share>> = impl.loadAll()

    override suspend fun getSingle(share: Share): Flow<List<Share>> = impl.loadSingle(share)

}