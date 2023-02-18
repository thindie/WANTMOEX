package com.example.thindie.wantmoex.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Success
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.result
import com.example.thindie.wantmoex.domain.unpackResult
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
        val state: CoinUIState
        if (!isLoading) {
            if (coin == null) {
                state = CoinUIState(coinsList = list, isLoading = isLoading)
            } else {
                state = CoinUIState(isLoading = isLoading, coin = coin)
            }
        } else {
            _coinList.value = emptyList(); _coin.value = null; _isLoading.value = false
            state = CoinUIState()
        }
        state.apply {
            Log.d("SERVICE_TAG", "${this.coinsList}")
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT),
        initialValue = savedStateHandle.get<CoinUIState>(VIEW_STATE) ?: CoinUIState()
    )


    private fun saveState() {
        savedStateHandle[VIEW_STATE] = viewState.value
    }

    fun onStart() {
        viewModelScope.launch {
            observeCoinList()
        }
    }

    fun onAddFavoriteCoins(list: List<String>) {
        viewModelScope.launch {
            doAddCoinToFavoritesUseCase(list)
        }
    }

    fun onDeleteFavoriteCoins(list: List<String>) {
        viewModelScope.launch {
            doDeleteCoinFromFavoritesUseCase(list)
        }
    }

    fun onExpandOptionsCoinsList() {

    }

    fun onShowFavorites() {

    }


    fun onShowList(size: Int?) {
        if (size == null) observeCoinList() else observeCoinList(size)
    }

    fun onChoseCoin(id: String) {
        observeCoin(id)
    }

    private fun getFavoriteCoinsByID(list: List<String>): Flow<List<CoinUIModel>> {
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
            val allCoins = mutableListOf<Coin>()
            val allFavoriteIds = mutableListOf<String>()

            getAllCryptoCoinsUseCase.observeAllCoins(coinsSize).collect { resultCoins ->
                val res = resultCoins
                resultCoins.result {
                    allCoins.addAll(it)
                }
            }
            getAllFavoriteCoinsUseCase().collect { resultIds ->
                resultIds.result {
                    allFavoriteIds.addAll(it)
                }
            }

            _coinList.value = allCoins.map {
                fromCoinToUIDeep(allFavoriteIds.contains(it.fromSymbol), it)
            }
            Log.d("SERVICE_TAG", "${_coinList.value}")
        }
    }


    private fun observeCoinList() {
        observeCoinList(TOP_COINS)
    }

    private fun observeCoin(id: String) {
        viewModelScope.launch {


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

    companion object {
        private const val TIMEOUT = 5000L
        private const val INIT_COINS = 30
        private const val TOP_COINS = 10
        private const val VIEW_STATE = "viewState"
    }
}



