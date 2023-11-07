package com.example.core.presentation.uicoin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.entities.Coin
import com.example.thindie.domain.usecase.FetchRequestUseCase
import com.example.thindie.domain.usecase.ObserveCoinsListUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoinsViewModel @Inject constructor(
    private val requestUseCase: FetchRequestUseCase,
    private val observeCoinsListUseCase: ObserveCoinsListUseCase,
) :
    ViewModel() {
    init {
        viewModelScope.launch {
            requestUseCase.invoke()
        }
    }

    val observe =
        observeCoinsListUseCase().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList<Coin>()
        )
}
