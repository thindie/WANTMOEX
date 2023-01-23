package com.example.thindie.wantmoex.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.useCases.DoSingleCoinRequestUseCase
import com.example.thindie.wantmoex.domain.useCases.GetAllEntitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllEntitiesUseCase: GetAllEntitiesUseCase,
    private val doSingleCoinRequestUseCase: DoSingleCoinRequestUseCase,
) : ViewModel() {


    private val _viewState: MutableStateFlow<CoinViewState> = MutableStateFlow(
        CoinViewState.Loading(
            Unit
        )
    )
    val viewState: StateFlow<CoinViewState>
        get() = _viewState


    init {
        onLoadCoinsList()
    }

    private fun onLoadCoinsList() {
        val coinList = mutableListOf<Coin>()
        viewModelScope.launch {
            getAllEntitiesUseCase.invoke().collect {
                coinList.addAll(it)
            }
        }
        viewModelScope.launch {
            delay(DELAY_TIMEOUT)
            if (coinList.isEmpty()) {
                CoinViewState.Error(Unit)
            }
            Log.d("SERVICE_TAG", coinList.toString())
        }
    }

    fun onRefresh() {

        viewModelScope.launch {
            while(_viewState.value is CoinViewState.SuccessCoinList){
                delay(REFRESH_TIMEOUT)
            }
        }
    }


    fun onLoadSingleCoin(fsym: String) {
        viewModelScope.launch {
            val coin: Coin? = doSingleCoinRequestUseCase.invoke(fsym)
            _viewState.value =
                if (coin == null) CoinViewState.Error(Unit) else CoinViewState.SuccessCoin(coin)
            Log.d("SERVICE_TAG", coin.toString())
        }
    }

    companion object{
        private const val REFRESH_TIMEOUT = 5000L
        private const val DELAY_TIMEOUT = 1500L
    }


    sealed class CoinViewState {
        data class SuccessCoinList(val coins: List<Coin>) : CoinViewState()
        data class SuccessCoin(val coins: Coin) : CoinViewState()
        data class Loading(val unit: Unit) : CoinViewState()
        data class Error(val unit: Unit) : CoinViewState()
    }
}