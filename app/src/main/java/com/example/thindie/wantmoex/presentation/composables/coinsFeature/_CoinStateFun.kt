package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.fromCoinToUI
import com.example.thindie.wantmoex.presentation.composables.util.ErrorScreen
import com.example.thindie.wantmoex.presentation.composables.util.LoadScreen


private const val START_TIME = 100L

@Composable

fun CoinStateFun(
    viewModel: CoinViewModel = viewModel(),
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit
) {

    val coinViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val toDetailCoinScreen: (String) -> Unit = { coinID -> viewModel.onLoadSingleCoin(coinID) }
    val toStartScreen: () -> Unit = viewModel::onLoadCoinsList

    when (coinViewState) {
        is CoinViewModel.CoinViewState.SuccessCoinList -> {
            CoinScreen(
                list = (coinViewState as CoinViewModel.CoinViewState.SuccessCoinList).coins.map {
                    fromCoinToUI(it)
                },
                onFavoritesAdded = onFavoritesAdded,
                onFavoritesDeleted = onFavoritesDeleted,
                onClickElement = toDetailCoinScreen,
                onClickFavorites = { viewModel.onLoadFavorites() },
                onClickBack = toStartScreen,
            )
        }
        is CoinViewModel.CoinViewState.SuccessCoin -> {
            CoinDetailsScreen(coin = fromCoinToUI((coinViewState as CoinViewModel.CoinViewState.SuccessCoin).coin)) {
                toStartScreen()
            }
        }
        is CoinViewModel.CoinViewState.SuccessFavoriteList -> {
            CoinFavoriteCoinsScreen(
                list = (coinViewState as CoinViewModel.CoinViewState.SuccessFavoriteList).coins.map(
                    fromCoinToUI),
                onFavoritesAdded = onFavoritesAdded,
                onFavoritesDeleted = onFavoritesDeleted,
                onClickFavourites = toStartScreen,
                onClickElement = toDetailCoinScreen
            ) {

            }
        }
        is CoinViewModel.CoinViewState.Loading -> {
            LoadScreen(waitTime = START_TIME, onTimeout = toStartScreen)
        }
        is CoinViewModel.CoinViewState.Error -> {
            ErrorScreen { }
        }

    }
}