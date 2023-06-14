package com.example.core.presentation.uicoinlist.coinslistscreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinsRoute
import com.example.core.presentation.uicoinlist.viewmodel.CoinsListViewModel

/**
 * [ScreenState] invokes [CoinsListScreen] depends on [isShowFavorites].
 * [ScreenState] used by [coinsListScreen] and [favoriteCoinsScreen]
 * triggers [viewModel] to show  favorites list or not
 *
 */
@Composable
internal fun ScreenState(
    screenState: CoinListScreenState,
    viewModel: CoinsListViewModel = hiltViewModel(),
    isShowFavorites: Boolean,
    fetch: (Boolean) -> Unit = viewModel::onFetchCoins,
    onClickCoin: () -> Unit,
) {
    screenState.onActivateSort = { isFavoriteCallback -> fetch(isFavoriteCallback) }
    fetch(isShowFavorites)
    val state = viewModel
        .coinsScreenState
        .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    CoinsListScreen(
        lazyListState = screenState.coinListState,
        onRefresh = { fetch(isShowFavorites) },
        isLoading = state.value.isLoading,
        coinsList = state.value.list,
        isWideScreen = screenState.isLandscape,
        isExpanded = screenState.isExpandedSection,
        onClickFavoriteCoin = viewModel::onChangeFavoriteState
    ) { ticker ->
        viewModel.onSetTicker(ticker)
        onClickCoin()
    }
}

fun NavGraphBuilder.coinsListScreen(
    state: CoinListScreenState,
    onClickCoin: () -> Unit
) {
    composable(route = coinsRoute) {
        ScreenState(
            screenState = state,
            isShowFavorites = state.isShowFavorites,
        ) { onClickCoin() }
    }
}
