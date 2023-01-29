package com.example.thindie.wantmoex.presentation

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


    fun onLoadCoinsList() {
        viewModelScope.launch {
            val flowAllCoins: Flow<List<Coin>> = getAllCryptoCoinsUseCase.invoke()
            val flowFavoriteCoins: Flow<List<Coin>> = getAllFavoriteCoinsUseCase.invoke()

            flowAllCoins.combine(flowFavoriteCoins) { flowAll, flowFavorites ->
                flowAll.map {
                    try {
                        fromCoinToUIDeep(it, flowFavorites.contains(it))
                    } catch (e: IndexOutOfBoundsException) {
                        fromCoinToUILazy(it)
                    }
                }
            }.collect {
                try {
                    it[INVOKE_BY_CONTROL]
                } catch (e: IndexOutOfBoundsException) {
                    _viewState.value = CoinViewState.Error; onLoadCoinsList(); return@collect
                }
                _viewState.value = CoinViewState.SuccessCoinList(it)
            }
        }
    }


    fun onLoadFavorites() {
        viewModelScope.launch {
            getAllFavoriteCoinsUseCase.invoke().collect { coinList ->
                try {
                    _viewState.value = CoinViewState.SuccessFavoriteList(
                        coinList.map { coin ->
                            fromCoinToUIDeep(coin, true)
                        })
                } catch (e: IndexOutOfBoundsException) {
                    _viewState.value = CoinViewState.Error
                    onLoadCoinsList()
                }
            }
        }
    }

    fun onLoadSingleCoin(coinTicker: String) {
        viewModelScope.launch {
            val coin: Coin? = doSingleCoinRequestUseCase.invoke(coinTicker)
            _viewState.value =
                if (coin == null) CoinViewState.Error else CoinViewState.SuccessCoin(
                    fromCoinToUILazy(coin)
                )
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

    sealed class CoinViewState {
        data class SuccessCoinList(val coins: List<CoinUIModel>) : CoinViewState()
        data class SuccessFavoriteList(val coins: List<CoinUIModel>) : CoinViewState()
        data class SuccessCoin(val coin: CoinUIModel) : CoinViewState()
        object Loading : CoinViewState()
        object Error : CoinViewState()
    }

    companion object {
        private const val INVOKE_BY_CONTROL = 1
    }
}