package com.example.thindie.wantmoex.presentation.composables.update

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

private const val ALL_COINS = "allCoins"
private const val FAVORITE_COINS = "favCoins"
private const val DETAIL_COINS = "detail"
private const val NEWS = "news"

private val cryptoBottomBarScreens = listOf(ALL_COINS, FAVORITE_COINS, DETAIL_COINS, NEWS)

@Composable
fun CryptoAppUI(

) {
    val navController = rememberNavController()
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack.value?.destination
    val currentScreen = cryptoDestinations.find { it.route == currentDestination?.route } ?: Coins


    Scaffold() {
        CryptoNavHost(navController = navController, modifier = Modifier.padding(it))
    }
}



