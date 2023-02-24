package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        state
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT),
        initialValue = CoinUIState(startLoading = true)
    )


    fun onRefresh(limit: Int) {
        viewModelScope.launch {
            observeCoinList(limit)
        }
    }

    fun onStart() {
        viewModelScope.launch {
            val list = getAllFavoriteCoinsUseCase.getAllFavoriteCoins().unpackResult { it }
            val listCoins = getAllCryptoCoinsUseCase.getAllCoins(TOP_COINS)
                .unpackResult { it.map { it.mapToUiModel { list?.contains(it) ?: false } } }
            _isLoading.value = false
            _coinList.value = listCoins ?: emptyList()
        }
    }

    fun onAddFavoriteCoins(id: String) {
        viewModelScope.launch {
            doAddCoinToFavoritesUseCase(id)
        }
    }

    fun onDeleteFavoriteCoins(id: String) {
        viewModelScope.launch {
            doDeleteCoinFromFavoritesUseCase(id)
        }
    }


    fun onShowFavorites() {
        _isLoading.value = true
        viewModelScope.launch {
            val coinsList = mutableListOf<CoinUIModel>()
            val idList = getAllFavoriteCoinsUseCase.getAllFavoriteCoins()
                .unpackResult { it }

            idList?.map {
                doSingleCoinRequestUseCase
                    .getCoin(it)
                    .unpackResult {
                        coinsList.add((it.mapToUiModel { true }))
                    }
            } ?: emptyList()
            _isLoading.value = false
            _coinList.value = coinsList

        }
    }


    fun onShowList(size: Int?) {
        if (size == null) observeCoinList() else observeCoinList(size)
    }

    fun onChoseCoin(id: String) {
        observeCoin(id)
    }

    private fun observeCoinList() {
        observeCoinList(TOP_COINS)
    }

    private fun observeCoinList(coinsSize: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            val ids = mutableListOf<String>()
            val coins = mutableListOf<CoinUIModel>()
            getAllFavoriteCoinsUseCase().onEach {
               ids.addAll(it.unpackResult { it } ?: emptyList())
            }.launchIn(viewModelScope)

            getAllCryptoCoinsUseCase.observeAllCoins(coinsSize).onEach {

                it.result { coins.addAll(it.map { it.mapToUiModel { ids.contains(it) } }) }
            }.launchIn(viewModelScope)
           delay(TO_LOAD_DATA)

            _isLoading.value = false
            _coinList.value = emptyList()
            _coinList.value = coins

        }

    }


    private fun observeCoin(id: String) {
        _isLoading.value = true
        viewModelScope.launch {

            doSingleCoinRequestUseCase(id).collect {
                it.result {
                    _isLoading.value = false
                    _coin.value = it.mapToUiModel { false }
                }
            }
        }
    }


    data class CoinUIState(
        val coinsList: List<CoinUIModel> = emptyList(),
        val isLoading: Boolean = true,
        val coin: CoinUIModel? = null,
        val startLoading: Boolean = false,
    )

    companion object {
        private const val TO_LOAD_DATA = 1600L
        private const val TIMEOUT = 5000L
        private const val TOP_COINS = 10
    }
}



