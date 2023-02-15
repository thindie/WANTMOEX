package com.example.thindie.wantmoex.presentation.composables.update

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun CryptoAppUI(

) {
    val navController = rememberNavController()
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack.value?.destination
    val currentScreen = cryptoDestinations.find { it.route == currentDestination?.route } ?: Coins


    Scaffold(bottomBar = { CryptoCoinsBottomBar(navController) }) {
        CryptoNavHost(navController = navController, modifier = Modifier.padding(it))
    }
}




