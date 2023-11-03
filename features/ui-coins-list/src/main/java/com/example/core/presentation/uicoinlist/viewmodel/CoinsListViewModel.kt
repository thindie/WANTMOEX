package com.example.core.presentation.uicoinlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.consts.sharingStarted
import com.example.core.domain.domaincoinslist.contract.CoinsListFetcher
import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.domain.domaincoinslist.entity.CoinsListSettings
import com.example.domain.domainsettings.contract.SettingsProvider
import com.example.domain.domainsettings.contract.TickerSetter
import com.example.domainfavorites.contract.FavoritesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Suppress("LongParameterList")
@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val fetcher: CoinsListFetcher,
    private val changeFavoriteState: FavoritesManager,
    private val tickerSetter: TickerSetter,
    private val settingsProvider: SettingsProvider
) : ViewModel() {

    private val previousFetchedJobMustBeCancel: MutableList<Job> = mutableListOf()
    private val coinListState: MutableStateFlow<List<Coin>> = MutableStateFlow(emptyList())
    internal val coinsScreenState: StateFlow<CoinScreenState> = coinListState
        .map { fetchedCoinsList ->
            if (fetchedCoinsList.isEmpty()) {
                CoinScreenState(emptyList(), isLoading = true)
            } else {
                CoinScreenState(fetchedCoinsList, isLoading = false)
            }
        }
        .stateIn(viewModelScope, sharingStarted, CoinScreenState())

    /**
     *  Executes a coin fetchCoinsListDependOn operation with the specified list settings.
     *  [isFavorites] Indicates whether the fetchCoinsListDependOn operation should target user favorites or not.
     *  as is current solution is - top settings bar activate [onFetchCoins] via callback,
     *  must cancel previous fetch every time new filter chosen, caused by
     *  unstable and wrong UI behavior
     **/
    @Inject
    internal fun onStart() {
        onFetchCoins(false)
    }

    internal fun onFetchCoins(isFavorites: Boolean = false) {
        previousFetchedJobMustBeCancel.forEach { previousJob -> previousJob.cancel() }
        viewModelScope.launch {
            fetcher.fetchCoinsListDependOn(
                ListSettings(
                    capacity = settingsProvider.getCapacity(),
                    isAsc = settingsProvider.getStraight(),
                    isFavorites = isFavorites,
                    sortedBy = settingsProvider.getSort()
                )
            )
                .onEach { fetchedCoinsList -> coinListState.value = fetchedCoinsList }
                .launchIn(this@launch)
                .apply { previousFetchedJobMustBeCancel += this@apply }
            // todo (
            // !!! in offline state current job will be cancelled due java net Unknown host exception
            // so fetchCoinsListDependOn will get previous ListSettings instance -> UI will be bounce.
            // fix that
        }
    }

    internal fun onChangeFavoriteState(ticker: String) = viewModelScope.launch {
        changeFavoriteState.changeFavoriteState(ticker)
    }

    internal fun onSetTicker(ticker: String) = tickerSetter(ticker)
    internal data class CoinScreenState(
        val list: List<Coin> = emptyList(),
        val isLoading: Boolean = true,
    )

    internal data class ListSettings(
        override val capacity: Int,
        override val sortedBy: String,
        override val isFavorites: Boolean,
        override val isAsc: Boolean,
    ) : CoinsListSettings
}
