package com.example.thindie.wantmoex.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllCryptoCoinsUseCase: GetAllCryptoCoinsUseCase,
    private val doSingleCoinRequestUseCase: DoSingleCoinRequestUseCase,
    private val doAddCoinToFavoritesUseCase: DoAddCoinToFavoritesUseCase,
    private val doDeleteCoinFromFavoritesUseCase: DoDeleteCoinFromFavoritesUseCase,
    private val getAllFavoriteCoinsUseCase: GetAllFavoriteCoinsUseCase
) : ViewModel() {


    private val _viewState: MutableStateFlow<CoinViewState> = MutableStateFlow(
        CoinViewState.Loading
    )
    val viewState: StateFlow<CoinViewState>
        get() = _viewState.asStateFlow()


    @Suppress("NAME_SHADOWING")
    fun onLoadCoinsList() {
        viewModelScope.launch {
            val flowAllCoins: Flow<List<Coin>?> = getAllCryptoCoinsUseCase()
            val flowFavoriteCoins: Flow<List<String>> = getAllFavoriteCoinsUseCase()

            flowAllCoins.combine(flowFavoriteCoins) { flowAllCoins, flowFavorites ->
                flowAllCoins?.map {
                    try {
                        fromCoinToUIDeep(it, flowFavorites.contains(it.fromSymbol))
                        //в том случае, когда один список длиннее другого
                    } catch (e: IndexOutOfBoundsException) {
                        fromCoinToUILazy(it)
                    }
                }
            }.collect {
                if (it.isNullOrEmpty()) {
                    _viewState.value = CoinViewState.Error; onLoadCoinsList(); return@collect
                }
                _viewState.value = CoinViewState.SuccessCoinList(it)
            }
        }


        fun onLoadFavorites() {
            try {
                viewModelScope.launch {
                    getAllFavoriteCoinsUseCase().collect { listOfIds ->
                        val coinUiModelList =  listOfIds.buildFavouriteCoinsList()
                        _viewState.value =
                            CoinViewState.SuccessFavoriteList(coinUiModelList)
                    }
                }
            } catch (e: Exception) {
                _viewState.value = CoinViewState.Error
                onLoadCoinsList()
            }
        }
    }


    fun onLoadSingleCoin(coinTicker: String) {
        viewModelScope.launch {
            val coin: Coin? = doSingleCoinRequestUseCase(coinTicker)
            _viewState.value =
                if (coin == null) CoinViewState.Error else CoinViewState.SuccessCoin(
                    fromCoinToUILazy(coin)
                )
        }
    }

    fun onAddToFavorites(coinNames: List<String>) {
        viewModelScope.launch {
            doAddCoinToFavoritesUseCase(coinNames)
        }
    }

    fun onDeleteFromFavorites(coinNames: List<String>) {
        viewModelScope.launch {
            doDeleteCoinFromFavoritesUseCase(coinNames)
        }
    }

    private suspend fun List<String>.buildFavouriteCoinsList(): List<CoinUIModel> {

        val coinUiModelList = mutableListOf<CoinUIModel>()
        this.forEach {
            val coin: Coin? = doSingleCoinRequestUseCase(it)
            if (coin != null) {
                coinUiModelList.add(fromCoinToUIDeep(coin, true))
            }
        }
        return coinUiModelList
    }


    sealed class CoinViewState {
        data class SuccessCoinList(val coins: List<CoinUIModel>) : CoinViewState()
        data class SuccessFavoriteList(val coins: List<CoinUIModel>) : CoinViewState()
        data class SuccessCoin(val coin: CoinUIModel) : CoinViewState()
        object Loading : CoinViewState()
        object Error : CoinViewState()
    }
}

