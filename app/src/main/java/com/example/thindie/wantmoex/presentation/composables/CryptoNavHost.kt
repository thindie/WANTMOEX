package com.example.thindie.wantmoex.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinDetailScreen
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinsScreen

@Composable
fun CryptoNavHost(
    navController: NavHostController,
    startDestination: CryptoDestination,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    val reNewUi: (String) -> Unit = { renewThat ->
        mapOf(
            Coins.route to { viewModel.onShowList(null) },
            FavoriteCoins.route to { viewModel.onShowFavorites() },
        )[renewThat]?.invoke()
    }

    val addFavoriteCoin = { it: String -> viewModel.onAddFavoriteCoins(it) }
    val deleteFavoriteCoin = { it: String -> viewModel.onDeleteFavoriteCoins(it) }

    Scaffold(bottomBar = {
        CryptoCoinsBottomBar(
            onSelectedDestination = { reNewUi(it); navController.navigateSingleTopTo(it) },
            onExpandCoins = { viewModel.onExpandCoinsList(state.value.coinsList) })
    }) {
        Box {
            NavHost(
                navController = navController,
                startDestination = startDestination.route,
                modifier = Modifier.padding(it)
            ) {
                composable(route = Coins.route) {
                    CryptoCoinsScreen(
                        onClickCoin = { route, id ->
                            viewModel.onChoseCoin(id);
                            navController.navigateSingleTopTo(route)
                        },
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        state = state.value
                    )
                }

                composable(route = CoinInFocus.route) {
                    state.value.coin?.let { CryptoCoinDetailScreen(coin = it) }
                }

                composable(route = News.route) { }

                composable(route = FavoriteCoins.route) {
                    CryptoCoinsScreen(
                        onClickCoin = { route, id ->
                            viewModel.onChoseCoin(id);
                            navController.navigateSingleTopTo(route)
                        },
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        state = state.value
                    )
                }
            }

        }

    }
}


