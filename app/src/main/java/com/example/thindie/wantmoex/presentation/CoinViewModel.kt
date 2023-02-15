package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.thindie.wantmoex.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllCryptoCoinsUseCase: GetAllCryptoCoinsUseCase,
    private val doSingleCoinRequestUseCase: DoSingleCoinRequestUseCase,
    private val doAddCoinToFavoritesUseCase: DoAddCoinToFavoritesUseCase,
    private val doDeleteCoinFromFavoritesUseCase: DoDeleteCoinFromFavoritesUseCase,
    private val getAllFavoriteCoinsUseCase: GetAllFavoriteCoinsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val _viewState: MutableStateFlow<CoinUIState> = MutableStateFlow(CoinUIState())
    val viewState: StateFlow<CoinUIState>
        get() = _viewState.asStateFlow()


}

data class CoinUIState(
    val coinsList: List<CoinUIModel> = emptyList(),
    val isLoading: Boolean = true,
    val coin: CoinUIModel? = null,
)

