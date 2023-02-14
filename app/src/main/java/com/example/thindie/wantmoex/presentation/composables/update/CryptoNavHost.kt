package com.example.thindie.wantmoex.presentation.composables.update

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CryptoNavHost(navController: NavHostController, modifier: Modifier) {


    NavHost(navController = navController, startDestination = Coins.route, modifier = modifier) {


        composable(route = Coins.route) {  }

        composable(route = CoinInFocus.route) { }

        composable(route = News.route) { }

        composable(route = FavoriteCoins.route) { }


    }

}


