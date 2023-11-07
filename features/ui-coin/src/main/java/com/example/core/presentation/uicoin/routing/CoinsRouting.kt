package com.example.core.presentation.uicoin.routing

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.presentation.uicoin.coinscreen.CoinsScreen
import com.example.core.presentation.uicoin.di.CoinFeatureComponent
import com.example.cryptoview.application.di.AppComponent

private const val COINS_ROUTE = "coins"

fun NavGraphBuilder.coinsRouting() {
    composable(route = COINS_ROUTE) {
        val activity = LocalContext.current
        if (activity is AppComponent) {
            val daggerComponent = CoinFeatureComponent.init(activity)
            val viewModelFactory = daggerComponent.viewModelsFactory()
            CoinsScreen(factory = viewModelFactory)
        }
    }
}
