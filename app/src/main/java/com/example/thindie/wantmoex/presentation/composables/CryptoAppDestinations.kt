package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Newspaper

import androidx.compose.ui.graphics.vector.ImageVector

interface CryptoDestination {
    val icon: ImageVector
    val route: String
}

object Coins : CryptoDestination {
    override val icon: ImageVector = Icons.Default.FormatListBulleted
    override val route: String = "Coins"
}

object CoinInFocus : CryptoDestination {
    override val icon: ImageVector = Icons.Default.List
    override val route: String = "Coin_in_focus"
}

object FavoriteCoins : CryptoDestination {
    override val icon: ImageVector = Icons.Default.Favorite
    override val route: String = "Favourites"
}

object News : CryptoDestination {
    override val icon: ImageVector = Icons.Default.Newspaper
    override val route: String = "News"
}

object CoinsExpandedView : CryptoDestination {
    override val icon: ImageVector = Icons.Default.Bookmark
    override val route: String = "Stars"
}


val cryptoDestinations = listOf(Coins, CoinInFocus, FavoriteCoins, News,  )