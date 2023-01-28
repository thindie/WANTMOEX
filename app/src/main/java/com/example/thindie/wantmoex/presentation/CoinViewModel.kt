package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _favoriteCache: MutableStateFlow<List<String>> =
        MutableStateFlow(emptyList<String>())
    val favoriteCache: StateFlow<List<String>>
        get() = _favoriteCache.asStateFlow()

    fun onLoadCoinsList() {
        viewModelScope.launch {
            getAllCryptoCoinsUseCase.invoke().collect {
                val coinList = it
                try {
                    coinList[0]
                    _viewState.value = CoinViewState.SuccessCoinList(coinList)
                } catch (e: IndexOutOfBoundsException) {
                    _viewState.value = CoinViewState.Error
                    onLoadCoinsList()
                }
            }
        }
    }

    fun onAddToFavorites(coinNames: List<String>) {
        viewModelScope.launch {
            doAddCoinToFavoritesUseCase.invoke(coinNames)
        }
    }

    fun onDeleteFromFavorites(coinNames: List<String>) {
        viewModelScope.launch {
            doDeleteCoinFromFavoritesUseCase.invoke(coinNames)
        }
    }

    fun onLoadFavorites() {
        viewModelScope.launch {
            getAllFavoriteCoinsUseCase.invoke().collect {
                val coinList = it
                try {
                    coinList[0]
                    _viewState.value = CoinViewState.SuccessCoinList(coinList)
                } catch (e: IndexOutOfBoundsException) {
                    _viewState.value = CoinViewState.Error
                    onLoadCoinsList()
                }
            }
        }
    }

    fun onLoadFavoritesIDs() {
        viewModelScope.launch {
            getAllFavoriteCoinsUseCase.invoke().collect {
                if (it.isNotEmpty()) {
                    val idList = it.map { coin -> coin.fromSymbol }
                    _favoriteCache.value = idList
                }
            }
        }
    }

    fun onLoadSingleCoin(fsym: String) {
        viewModelScope.launch {
            val coin: Coin? = doSingleCoinRequestUseCase.invoke(fsym)
            _viewState.value =
                if (coin == null) CoinViewState.Error else CoinViewState.SuccessCoin(coin)
        }
    }

    sealed class CoinViewState {
        data class SuccessCoinList(val coins: List<Coin>) : CoinViewState()
        data class SuccessCoin(val coin: Coin) : CoinViewState()
        object Loading : CoinViewState()
        object Error : CoinViewState()
    }
}