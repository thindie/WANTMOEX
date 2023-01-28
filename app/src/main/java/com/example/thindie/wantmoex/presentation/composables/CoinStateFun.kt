package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.CoinViewModel


private const val START_TIME = 2000L

@Composable

fun CoinStateFun(viewModel: CoinViewModel = viewModel()) {
    val coinViewState by viewModel.viewState.collectAsStateWithLifecycle()



    when (coinViewState) {
        is CoinViewModel.CoinViewState.SuccessCoinList -> {
            CoinScreen(
                list = (coinViewState as CoinViewModel.CoinViewState.SuccessCoinList).coins,
                onClickElement = { coinID ->
                    viewModel.onLoadSingleCoin(coinID)
                },
                onClickBack = { viewModel.onLoadCoinsList() },
                onClickFavourites = { viewModel.onLoadFavorites() },
               )
        }
        is CoinViewModel.CoinViewState.SuccessCoin -> {
            CoinScreen(
                list = listOf((coinViewState as CoinViewModel.CoinViewState.SuccessCoin).coin),
                onClickElement = {},
                onClickBack = { viewModel.onLoadCoinsList() },
                onClickFavourites = { viewModel.onLoadFavorites() },
            )
        }
        is CoinViewModel.CoinViewState.Loading -> {
            CoinLoadScreen(waitTime = START_TIME) {
                viewModel.onLoadCoinsList()
            }
        }
        is CoinViewModel.CoinViewState.Error -> {
            CoinErrorScreen {

            }
        }

    }
}