package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.StockApiService
import com.example.thindie.wantmoex.domain.StockRepository
import javax.inject.Inject

class StockDataRepositoryImpl @Inject constructor(
    private val stockApiService: StockApiService
) : StockRepository {


}