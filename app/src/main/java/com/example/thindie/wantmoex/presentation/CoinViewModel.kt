package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.R
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
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _coin: MutableStateFlow<CoinUIModel?> = MutableStateFlow(null)


    val viewState: StateFlow<CoinUIState> = combine(
        _coinList, _isLoading, _coin
    ) { list, isLoading, coin ->
        if (!isLoading) {
            if (coin == null) {
                CoinUIState(coinsList = list, isLoading = isLoading)
            } else {
                CoinUIState(isLoading = isLoading, coin = coin)
            }
        } else {
            _coinList.value = emptyList(); _coin.value = null; _isLoading.value = false
            CoinUIState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinUIState()
    )

    fun onStart() {
        viewModelScope.launch {
            getAllCryptoCoinsUseCase.getAllCoins(30)
        }
        viewModelScope.launch {
            observeCoinList()
        }
    }

    fun onExpandOptionsCoinsList() {

        viewModelScope.launch {
            val list = _coinList.value
            list.map { it.expandChange() }.map {
                val checkFavorite = getAllFavoriteCoinsUseCase.checkIsFavorite(it.fromSymbol)
                it.isFavorite {
                    checkFavorite
                }
            }

        }

    }

    fun onShowFavorites() {
        viewModelScope.launch {
            load()
            getAllFavoriteCoinsUseCase().collect { favorites ->
                when (favorites) {

                    is Success -> {
                        val flow = favorites.unpackResult {
                            getCoinsByListId(it)
                        }
                        flow.collect {
                            _coinList.value = it
                        }
                    }
                    is Results.Error -> {

                    }

                }
            }

        }
    }


    fun onShowList(size: Int?) {
        if (size == null) observeCoinList() else observeCoinList(size)
    }

    fun onChoseCoin(id: String) {
        observeCoin(id)
    }

    private fun getCoinsByListId(list: List<String>): Flow<List<CoinUIModel>> {
        return flow {

            val uiList = mutableListOf<CoinUIModel>()
            list.forEach {
                val coinResult = doSingleCoinRequestUseCase.getCoin(it)
                when (coinResult) {
                    is Success -> coinResult.unpackResult {
                        uiList.add(
                            fromCoinToUILazy(it).copy(
                                isFavorite = true,
                                isShowExpand = true
                            )
                        )
                    }
                    is Results.Error -> {
                        _coinList.value = emptyList()
                    }
                }
            }
            emit(uiList)
        }
    }

    private fun observeCoinList(coinsSize: Int) {
        viewModelScope.launch {
            load()
            getAllCryptoCoinsUseCase.observeAllCoins(coinsSize).collect { result ->
                when (result) {
                    is Success -> {
                        _coinList.value = result.unpackResult { list ->
                            list.map { fromCoinToUILazy(it) }
                        }
                    }
                    is Results.Error -> {

                    }
                }
            }
        }
    }

    private fun observeCoinList() {
        observeCoinList(10)
    }

    private fun observeCoin(id: String) {
        viewModelScope.launch {
           load()
            doSingleCoinRequestUseCase(id).collect {
                when (it) {
                    is Success -> {
                        it.unpackResult {
                            _coin.value = fromCoinToUILazy(it)
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private suspend fun load() {
        _isLoading.value = true
        delay(1)
        _isLoading.value = false
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
