package com.example.cryptoview.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.usecase.FetchRequestUseCase
import com.example.thindie.domain.usecase.ObserveCoinsListUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(
    observe: ObserveCoinsListUseCase,
    private val requestFetch: FetchRequestUseCase,
) :
    ViewModel() {
    init {
        viewModelScope.launch {
            requestFetch()
        }
    }

    val listState = observe()
}
