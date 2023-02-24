package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun CryptoAppUI() {
    val navController = rememberNavController()
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack.value?.destination
    val currentScreen = cryptoDestinations.find { it.route == currentDestination?.route } ?: Coins

    CryptoNavHost(
        navController = navController,
        startDestination = currentScreen,
    )
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id)
        {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }



