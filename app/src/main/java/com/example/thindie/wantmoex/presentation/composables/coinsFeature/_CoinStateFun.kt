package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.util.ErrorScreen
import com.example.thindie.wantmoex.presentation.composables.util.LoadScreen


private const val START_TIME = 100L

@Composable

fun CoinStateFun(viewModel: CoinViewModel = viewModel()) {

    val coinViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val favoriteIds by viewModel.favoriteCache.collectAsStateWithLifecycle()

    when (coinViewState) {
        is CoinViewModel.CoinViewState.SuccessCoinList -> {
            CoinScreen(
                list = (coinViewState as CoinViewModel.CoinViewState.SuccessCoinList).coins,
                onClickElement = { coinID -> viewModel.onLoadSingleCoin(coinID) },
                onClickBack = { viewModel.onLoadCoinsList() },
                onClickFavourites = { viewModel.onLoadFavorites() },
                favoriteList = favoriteIds,
                onFavoritesAdded = {
                    viewModel.onAddToFavorites(listOf(it))
                },
                onFavoritesDeleted = {
                    viewModel.onDeleteFromFavorites(listOf(it))
                },
            )
        }
        is CoinViewModel.CoinViewState.SuccessCoin -> {
            CoinDetailsScreen(coin = (coinViewState as CoinViewModel.CoinViewState.SuccessCoin).coin) {
                viewModel.onLoadCoinsList()
            }
        }
        is CoinViewModel.CoinViewState.Loading -> {
            LoadScreen(waitTime = START_TIME, onTimeout = viewModel::onLoadCoinsList)
        }
        is CoinViewModel.CoinViewState.Error -> {
            ErrorScreen { }
        }

    }
}