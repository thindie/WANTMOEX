package com.example.thindie.wantmoex.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.data.transform
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Success
import com.example.thindie.wantmoex.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    private val _coinList: MutableStateFlow<List<CoinUIModel>> = MutableStateFlow(emptyList())
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _coin: MutableStateFlow<CoinUIModel?> = MutableStateFlow(null)


    val viewState: StateFlow<CoinUIState> = combine(
        _coinList, _isLoading, _coin
    ) { list, isLoading, coin ->
        if (!isLoading) {
            if (coin == null) {
                CoinUIState(coinsList = list, isLoading = isLoading)
            } else CoinUIState(coin = coin, isLoading = isLoading)
        } else CoinUIState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinUIState()
    )


    private fun observeCoinList() {
        viewModelScope.launch {
            getAllCryptoCoinsUseCase().collect { result ->
                when (result) {
                    is Success -> {
                        _coinList.value = result.unpackResult { list ->
                            list.map { fromCoinToUILazy(it) }
                        }
                        _isLoading.value = false
                    }
                    is Results.Error -> {

                        getAllCryptoCoinsUseCase.getAllCoins()

                    }
                }
            }
        }

    }

    fun onStart() {
        viewModelScope.launch {
            delay( 100)
            observeCoinList()
        }
        viewModelScope.launch {
            delay( 2000)
            _isLoading.value = true
        }
        viewModelScope.launch {
            delay( 5000)
             when (val result =doSingleCoinRequestUseCase.getCoin("XRP")){
                is Success -> { result.unpackResult {
                    Log.d("SERVICE_TAG", it.fromSymbol)
                    _isLoading.value = false
                    _coin.value = fromCoinToUILazy(it)
                }}
                else -> {}
            }
        }
        viewModelScope.launch {
            delay( 9000)

             doSingleCoinRequestUseCase("ETH").collect{
                 when(it){
                     is Success -> { it.unpackResult {
                         Log.d("SERVICE_TAG", it.fromSymbol)
                         _isLoading.value = false
                         _coin.value = fromCoinToUILazy(it)
                     }}
                     else -> {}
                 }

             }
         }
     }

    data class CoinUIState(
        val coinsList: List<CoinUIModel> = emptyList(),
        val isLoading: Boolean = true,
        val coin: CoinUIModel? = null,
    )
}

fun <T, R> Success<T>.unpackResult(mapper: (T) -> R): R {
    return mapper(this.data)
}
