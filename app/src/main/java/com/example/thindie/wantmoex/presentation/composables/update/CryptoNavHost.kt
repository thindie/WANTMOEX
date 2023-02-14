package com.example.thindie.wantmoex.presentation.composables.update

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun CryptoNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Coins.route, modifier = modifier) {

    }
}
