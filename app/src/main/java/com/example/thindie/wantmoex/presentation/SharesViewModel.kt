package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import com.example.thindie.wantmoex.data.StockDataRepositoryImpl
import com.example.thindie.wantmoex.di.DaggerWMComponent
import javax.inject.Inject


class SharesViewModel() : ViewModel() {
    @Inject
    lateinit var impl: StockDataRepositoryImpl

    init {
        DaggerWMComponent.create().inject(this)
    }

}