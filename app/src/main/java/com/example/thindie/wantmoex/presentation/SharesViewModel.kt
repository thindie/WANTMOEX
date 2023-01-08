package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.data.StockDataRepositoryImpl
import com.example.thindie.wantmoex.di.DaggerWMComponent
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.domain.useCases.GetAllEntiesUseCase
import com.example.thindie.wantmoex.domain.useCases.GetSingleEntity
import kotlinx.coroutines.launch
import javax.inject.Inject


class SharesViewModel() : ViewModel() {
    @Inject
    lateinit var impl: StockDataRepositoryImpl
    @Inject
    lateinit var getAllEntiesUseCase: GetAllEntiesUseCase
    @Inject
    lateinit var getSingleEntity: GetSingleEntity

    init {
        DaggerWMComponent.create().inject(this)
    }

    fun fetchData() {
        viewModelScope.launch {
            getAllEntiesUseCase.invoke().collect {
                
            }
            getSingleEntity.invoke(Share("AFLT","s", "D")).collect{
                println(it.toString())
            }
        }

    }

}