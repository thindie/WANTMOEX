package com.example.core.presentation.uicoin.routing

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.presentation.uicoin.coinscreen.CoinsScreen


private const val COINS_ROUTE = "coins"

fun NavGraphBuilder.coinsRouting(factory: ViewModelProvider.Factory) {
    composable(route = COINS_ROUTE) {
        CoinsScreen(factory = factory)
    }
}

