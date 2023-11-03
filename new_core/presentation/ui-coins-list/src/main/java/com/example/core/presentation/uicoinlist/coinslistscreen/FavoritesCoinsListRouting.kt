package com.example.core.presentation.uicoinlist.coinslistscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.designelements.cryptoroutes.CryptoRoutes

fun NavGraphBuilder.favoriteCoinsScreen(
    state: CoinListScreenState,
    onClickCoin: () -> Unit
) {
    composable(route = CryptoRoutes.favoritesRoute) {
        ScreenState(
            screenState = state,
            isShowFavorites = state.isShowFavorites
        ) { onClickCoin() }
    }
}
