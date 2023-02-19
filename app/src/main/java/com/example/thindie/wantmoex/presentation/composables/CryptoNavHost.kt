package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinDetailScreen
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinsScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun CryptoNavHost(
    navController: NavHostController,
    startDestination: CryptoDestination,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    val reNewUi: (String, nullableParam: String?) -> Unit = { renewThat, param ->
        mapOf(
            Coins.route to { viewModel.onShowList(null) },
            FavoriteCoins.route to { viewModel.onShowFavorites() },
            News.route to {},
            CoinInFocus.route to { viewModel.onChoseCoin(param!!); }
        )[renewThat]?.invoke()
    }

    val addFavoriteCoin = { it: String -> viewModel.onAddFavoriteCoins(it) }
    val deleteFavoriteCoin = { it: String -> viewModel.onDeleteFavoriteCoins(it) }

    Scaffold(bottomBar = {
        CryptoCoinsBottomBar(
            onSelectedDestination = { reNewUi(it, null); navController.navigateSingleTopTo(it) },
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
                            reNewUi(route, id)
                            navController.navigateSingleTopTo(route)
                        },
                        onRefresh = {},
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
                            reNewUi(route, id)
                            navController.navigateSingleTopTo(route)
                        },
                        onRefresh = {},
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        state = state.value
                    )
                }
            }

        }

    }
}


