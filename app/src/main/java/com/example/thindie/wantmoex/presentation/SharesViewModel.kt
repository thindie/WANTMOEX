package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import com.example.thindie.wantmoex.data.StockDataRepositoryImpl
import javax.inject.Inject


class SharesViewModel @Inject constructor(private val impl: StockDataRepositoryImpl) : ViewModel() {


}