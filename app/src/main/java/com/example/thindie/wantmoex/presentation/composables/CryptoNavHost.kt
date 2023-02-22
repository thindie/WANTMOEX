package com.example.thindie.wantmoex.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.thindie.wantmoex.presentation.composables.newsScreen.CryptoNewsScreen
import com.example.thindie.wantmoex.presentation.composables.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val INITIAL_COINS_CAPACITY = 10

@Composable
fun CryptoNavHost(
    navController: NavHostController,
    startDestination: CryptoDestination,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CoinViewModel = hiltViewModel(),
) {

    val state = viewModel.viewState.collectAsStateWithLifecycle()
    var topAppLabel by remember { mutableStateOf(R.string.coins) }
    var newsTagListState = remember { mutableStateListOf<String>(BTC, ETH, DOGE, SHIBA, XRP) }
    var coinsLimitState by rememberSaveable { mutableStateOf(INITIAL_COINS_CAPACITY) }
    Log.d("SERVICE_TAG", coinsLimitState.toString())

    val reNewUi: (String, nullableParam: String?) -> Unit = { renewThat, param ->
        mapOf(
            Coins.route to { viewModel.onShowList(coinsLimitState); topAppLabel = R.string.coins },
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
        scaffoldState = scaffoldState,
        topBar = { CryptoTopAppbar(resource = topAppLabel) { coroutineScope.launch { scaffoldState.drawerState.open() } } },
        bottomBar = {
            CryptoCoinsBottomBar(onSelectedDestination = {
                reNewUi(it, null)
                navController.navigateSingleTopTo(it)
            },
                onExpandCoins = { viewModel.onExpandCoinsList(state.value.coinsList) })
        },
        drawerContent = {
            AppDrawer(
                onSelectTags = {
                    newsTagListState = it.toMutableStateList();
                },
                onSelectedLimit = { coinsLimitState = it },
                closeDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } },
            )
        })


    {
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
                        onRefresh = { viewModel.onRefresh(coinsLimitState) },
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        isLoading = state.value.isLoading,
                        coinList = state.value.coinsList
                    )
                }

                composable(route = CoinInFocus.route) {
                    state.value.coin?.let { coinUIModel ->
                        CryptoCoinDetailScreen(coin = coinUIModel) {
                            reNewUi(Coins.route, null)
                            navController.navigateSingleTopTo(it)
                        }
                    }
                }

                composable(route = News.route) { CryptoNewsScreen(tagList = newsTagListState.toList()) }

                composable(route = FavoriteCoins.route) {
                    CryptoCoinsScreen(
                        onClickCoin = { route, id ->
                            reNewUi(route, id)
                            navController.navigateSingleTopTo(route)
                        },
                        onRefresh = viewModel::onShowFavorites,
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        isLoading = state.value.isLoading,
                        coinList = state.value.coinsList
                    )
                }
            }
        }
    }
}


