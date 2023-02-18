package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.result
import com.example.thindie.wantmoex.domain.unpackResult
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
        state
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

    fun onExpandCoinsList(list: List<CoinUIModel>) {
        _coinList.value = list.map {
            it.copy(isShowExpand = !it.isShowExpand)
        }
    }

    fun onShowFavorites() {
        _isLoading.value = true
        viewModelScope.launch {

            val coinUIModelList = mutableListOf<CoinUIModel>()
            getAllFavoriteCoinsUseCase().collect { inResults ->
                inResults.result { favoriteIds ->
                    favoriteIds.forEach { id ->
                        doSingleCoinRequestUseCase(id).onEach {
                            it.result { coin ->
                                coinUIModelList.add(coin.mapToUiModel { true })  //favoriteCoin = true
                            }
                        }.launchIn(viewModelScope)
                    }
                }
            }
            _isLoading.value = false
            _coinList.value = coinUIModelList
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
            val list = getAllFavoriteCoinsUseCase.getAllFavoriteCoins().unpackResult { it }
            val listCoins = getAllCryptoCoinsUseCase.getAllCoins(coinsSize)
                .unpackResult { it.map { it.mapToUiModel { list.contains(it) } } }
            _isLoading.value = false
            _coinList.value = listCoins
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
    )

    companion object {
        private const val TIMEOUT = 5000L
        private const val INIT_COINS = 30
        private const val TOP_COINS = 10
        private const val VIEW_STATE = "viewState"
    }
}



