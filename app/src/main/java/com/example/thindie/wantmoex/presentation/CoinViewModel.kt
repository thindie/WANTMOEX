package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.useCases.DoSingleCoinRequestUseCase
import com.example.thindie.wantmoex.domain.useCases.GetAllCryptoCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllCryptoCoinsUseCase: GetAllCryptoCoinsUseCase,
    private val doSingleCoinRequestUseCase: DoSingleCoinRequestUseCase,
) : ViewModel() {


    private val _viewState: MutableStateFlow<CoinViewState> = MutableStateFlow(
        CoinViewState.Loading
    )
    val viewState: StateFlow<CoinViewState>
        get() = _viewState.asStateFlow()

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