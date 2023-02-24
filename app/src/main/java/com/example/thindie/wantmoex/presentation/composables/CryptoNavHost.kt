package com.example.thindie.wantmoex.presentation.composables

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinDetailScreen
import com.example.thindie.wantmoex.presentation.composables.coinScreen.CryptoCoinsScreen
import com.example.thindie.wantmoex.presentation.composables.coinScreen.FavoriteCoinsList
import com.example.thindie.wantmoex.presentation.composables.newsScreen.CryptoNewsScreen
import com.example.thindie.wantmoex.presentation.composables.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val INITIAL_COINS_CAPACITY = 10
private const val NEWS = "news"
private const val FAVORITES = "favorites"
private const val COIN = "coin"
private const val COINS = "coins"
private const val TAGS = "tags"
private val INIT_TAGS = arrayOf(BTC, ETH, DOGE, SHIBA, XRP)


@Composable
fun CryptoNavHost(
    navController: NavHostController,
    startDestination: CryptoDestination,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CoinViewModel = hiltViewModel(),
) {
    var isFirstLaunch by rememberSaveable { mutableStateOf(true) }
    if (isFirstLaunch) {
        viewModel.onStart(); isFirstLaunch = false
    }


    val state = viewModel.viewState.collectAsStateWithLifecycle()


    var topAppLabel by remember { mutableStateOf(R.string.loading) }
    val newsTagListState = remember { mutableStateListOf(BTC, ETH, DOGE, SHIBA, XRP) }
    var coinsLimitState by rememberSaveable { mutableStateOf(INITIAL_COINS_CAPACITY) }
    var expandedCoins by rememberSaveable { mutableStateOf(false) }

    val reNewUi: (String, nullableParam: String?) -> Unit = { renewThat, param ->

        mapOf(
            Coins.route to { viewModel.onShowList(coinsLimitState) },
            FavoriteCoins.route to { viewModel.onShowFavorites() },
            News.route to { },
            CoinInFocus.route to { viewModel.onChoseCoin(param!!) }
        )[renewThat]?.invoke()
    }

    val addFavoriteCoin = { it: String -> viewModel.onAddFavoriteCoins(it) }
    val deleteFavoriteCoin = { it: String -> viewModel.onDeleteFavoriteCoins(it) }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CryptoTopAppbar(
                resource = topAppLabel,
                tags = newsTagListState.toList(),
                destination = startDestination
            ) { coroutineScope.launch { scaffoldState.drawerState.open() } }
        },
        bottomBar = {
            CryptoCoinsBottomBar(onSelectedDestination = {
                reNewUi(it, null)
                navController.navigateSingleTopTo(it)
            },
                onExpandCoins = {  expandedCoins = !expandedCoins })
        },
        drawerContent = {
            AppDrawer(
                onSelectTags = {
                    newsTagListState.clear()
                    it.forEach { newsTagListState.add(it) }
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
                composable(
                    route = Coins.route,
                    arguments = listOf(
                        navArgument(COINS) {
                            type = NavType.IntType; defaultValue = R.string.coins
                        })
                )
                {
                    topAppLabel = it.arguments?.getInt(COINS)!!
                    CryptoCoinsScreen(
                        onClickCoin = { route, id ->
                            reNewUi(route, id)
                            navController.navigateSingleTopTo(route)
                        },
                        onRefresh = { viewModel.onRefresh(coinsLimitState) },
                        onFavoritesAdded = { addFavoriteCoin(it) },
                        onFavoritesDeleted = { deleteFavoriteCoin(it) },
                        isLoading = state.value.isLoading,
                        coinList = state.value.coinsList,
                        isExpanded = expandedCoins
                    )
                }

                composable(
                    route = CoinInFocus.route,
                    arguments = listOf(
                        navArgument(COIN) {
                            type = NavType.IntType; defaultValue = R.string.coin_details
                        })
                ) {
                    topAppLabel = it.arguments?.getInt(COIN)!!
                    state.value.coin?.let { coinUIModel ->
                        CryptoCoinDetailScreen(coin = coinUIModel,
                            onFavoritesAdded = { addFavoriteCoin(coinUIModel.fromSymbol) },
                            onReadNewsAbout = {
                                newsTagListState.clear()
                                newsTagListState.add(coinUIModel.fromSymbol)
                                navController.navigateSingleTopTo(News.route)
                            }
                        ) {
                            reNewUi(Coins.route, null)
                            navController.navigateSingleTopTo(it)
                        }
                    }
                }

                composable(
                    route = News.route,
                    arguments = listOf(
                        navArgument(NEWS) { type = NavType.IntType; defaultValue = R.string.news },
                        navArgument(TAGS) {
                            type = NavType.StringArrayType; defaultValue = INIT_TAGS
                        }
                    )
                ) {
                    topAppLabel = it.arguments?.getInt(NEWS)!!
                    CryptoNewsScreen(tagList = newsTagListState.toList())
                }

                composable(
                    route = FavoriteCoins.route,
                    arguments = listOf(
                        navArgument(FAVORITES) {
                            type = NavType.IntType; defaultValue = R.string.favorites
                        })
                ) {
                    topAppLabel = it.arguments?.getInt(FAVORITES)!!
                    FavoriteCoinsList(
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





