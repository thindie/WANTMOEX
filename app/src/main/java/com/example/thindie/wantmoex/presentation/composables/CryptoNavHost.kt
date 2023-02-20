package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinDetailScreen
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinsScreen
import com.example.thindie.wantmoex.presentation.composables.util.surfaceColor
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
    var topAppLabel by remember { mutableStateOf(R.string.coins) }

    val reNewUi: (String, nullableParam: String?) -> Unit = { renewThat, param ->
        mapOf(
            Coins.route to { viewModel.onShowList(null); topAppLabel = R.string.coins },
            FavoriteCoins.route to {
                viewModel.onShowFavorites(); topAppLabel = R.string.favorites
            },
            News.route to { topAppLabel = R.string.news },
            CoinInFocus.route to {
                viewModel.onChoseCoin(param!!); topAppLabel = R.string.coin_details
            }
        )[renewThat]?.invoke()
    }

    val addFavoriteCoin = { it: String -> viewModel.onAddFavoriteCoins(it) }
    val deleteFavoriteCoin = { it: String -> viewModel.onDeleteFavoriteCoins(it) }

    Scaffold(
        topBar = { CryptoTopAppbar(resource = topAppLabel, {}) },
        bottomBar = {
            CryptoCoinsBottomBar(
                onSelectedDestination = {
                    reNewUi(
                        it,
                        null
                    ); navController.navigateSingleTopTo(it)
                },
                onExpandCoins = { viewModel.onExpandCoinsList(state.value.coinsList) })
        }) {
        Box {
            NavHost(
                navController = navController,
                startDestination = startDestination.route,
                modifier = Modifier
                    .padding(it)
                    .surfaceColor()
            ) {
                composable(route = Coins.route) {
                    CryptoCoinsScreen(
                        onClickCoin = { route, id ->
                            reNewUi(route, id)
                            navController.navigateSingleTopTo(route)
                        },
                        onRefresh = viewModel::onRefresh,
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
                        onRefresh = viewModel::onRefresh,
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        state = state.value
                    )
                }
            }

        }

    }
}


