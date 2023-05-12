package com.example.core.presentation.uicoin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.domaincoin.contract.CoinFetcher
import com.example.core.domain.domaincoin.entity.Coin
import com.example.domain.domainsettings.contract.NewsTagsSetter
import com.example.domainfavorites.contract.FavoritesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CoinItemViewModel @Inject constructor(
    private val fetcher: CoinFetcher,
    private val manager: FavoritesManager,
    private val tagSetter: NewsTagsSetter,
) : ViewModel() {
    private val _coinState: MutableStateFlow<CoinUiState> = MutableStateFlow(CoinUiState())
    val coinState: StateFlow<CoinUiState>
        get() = _coinState.asStateFlow()

    internal fun onLoadCoin() {
        viewModelScope.launch {
            _coinState.value = CoinUiState(coin = fetcher(), false)
        }
    }

    internal fun onPinFavorite(ticker: String) = viewModelScope.launch {
        manager.changeFavoriteState(ticker)
    }

    internal fun onReadCoinNews(coinTicker: String) {
        viewModelScope.launch {
            tagSetter.setTags(listOf(coinTicker))
        }
    }

    companion object {
        data class CoinUiState(
            val coin: Coin? = null,
            val isLoading: Boolean = false,
        )
    }
}
