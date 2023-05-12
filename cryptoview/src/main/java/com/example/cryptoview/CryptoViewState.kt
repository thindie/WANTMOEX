package com.example.cryptoview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptoview.navigation.Coins
import com.example.cryptoview.navigation.cryptoDestinations

@Composable
internal fun rememberCryptoViewState(
    navHostController: NavHostController = rememberNavController(),
    theme: Boolean,
    windowSizeClass: Boolean,
): CryptoViewState {
    return remember(navHostController, theme, windowSizeClass) {
        CryptoViewState(navHostController, windowSizeClass)
    }
}

@Stable
internal class CryptoViewState(
    val navController: NavHostController,
    windowSizeClass: Boolean,
) {
    private val entryState: State<NavBackStackEntry?>
        @Composable get() = navController.currentBackStackEntryAsState()

    val isShowIconRail: Boolean = windowSizeClass
    val isShowTopBar: Boolean = !isShowIconRail
    val isWideScreen: Boolean = windowSizeClass

    private val currentDestination: NavDestination?
        @Composable get() = entryState.value?.destination

    val currentRoute: String
        @Composable get() =
            cryptoDestinations
                .find { it.route == currentDestination?.route }
                .let { it?.route ?: Coins.route }

    val startDestination: String
        @Composable get() = Coins.route

    fun navigate(route: String) {
        navController.navigateSingleTopTo(route)
    }

    private fun NavHostController.navigateSingleTopTo(route: String) =
        this.navigate(route) {
            popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
}
