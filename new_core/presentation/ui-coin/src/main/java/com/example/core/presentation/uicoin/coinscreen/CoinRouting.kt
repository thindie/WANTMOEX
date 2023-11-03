package com.example.core.presentation.uicoin.coinscreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinRoute
import com.example.core.presentation.uicoin.viewmodel.CoinItemViewModel

fun NavGraphBuilder.coinItem(isWide: Boolean, onBackPress: () -> Unit, onWatchNews: () -> Unit) {
    composable(
        route = coinRoute
    ) {
        CoinScreenState(onBackPress = onBackPress, isWideScreen = isWide) {
            onWatchNews()
        }
    }
}

@Composable
internal fun CoinScreenState(
    viewModel: CoinItemViewModel = hiltViewModel(),
    isWideScreen: Boolean,
    onBackPress: () -> Unit,
    onWatchNews: () -> Unit,
) {
    viewModel.onLoadCoin()
    val state =
        viewModel.coinState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    val coin = state.value.coin
    if (coin != null) {
        CoinItemScreen(
            coin = coin,
            onWatchNews = { ticker -> viewModel.onReadCoinNews(ticker); onWatchNews() },
            onAddTicker = viewModel::onPinFavorite,
            isWideScreen = isWideScreen
        ) { onBackPress() }
    }
}
