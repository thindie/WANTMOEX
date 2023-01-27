package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.CoinViewModel

@Composable

fun StartScreen(viewModel: CoinViewModel = viewModel()) {
    val coinViewState by viewModel.viewState.collectAsStateWithLifecycle()

    when (coinViewState) {
        is CoinViewModel.CoinViewState.SuccessCoinList -> {
            CoinHomeScreen(
                list = (coinViewState as CoinViewModel.CoinViewState.SuccessCoinList).coins,
                onClickElement = { coinID ->
                    viewModel.onLoadSingleCoin(coinID)
                },
                onClickBack = { viewModel.onLoadCoinsList() })
        }
        is CoinViewModel.CoinViewState.SuccessCoin -> {
            CoinHomeScreen(
                list = listOf((coinViewState as CoinViewModel.CoinViewState.SuccessCoin).coin),
                onClickElement = {},
                onClickBack = { viewModel.onLoadCoinsList() })
        }
        is CoinViewModel.CoinViewState.Loading -> {
            CoinLoadScreen {
                viewModel.onLoadCoinsList()
            }
        }
        is CoinViewModel.CoinViewState.Error -> {
            CoinErrorScreen {

            }
        }

    }
}