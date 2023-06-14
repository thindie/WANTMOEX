package com.example.cryptoview.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.designelements.R
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinsRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.expandsRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.favoritesRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.news
import com.example.core.designelements.icons.ShortHandedIcons.bulleted
import com.example.core.designelements.icons.ShortHandedIcons.heart
import com.example.core.designelements.icons.ShortHandedIcons.list
import com.example.core.designelements.icons.ShortHandedIcons.newsPaper

object Coins : Destinations {
    override val icon: ImageVector = list
    override val label: Int = R.string.coins_label
    override val route: String = coinsRoute
}

object FocusCoin : Destinations {
    override val icon: ImageVector = list
    override val label: Int = R.string.focus_coin
    override val route: String = coinRoute
}

object FavoriteCoins : Destinations {
    override val icon: ImageVector = heart
    override val label: Int = R.string.favorite_label
    override val route: String = favoritesRoute
}

object News : Destinations {
    override val icon: ImageVector = newsPaper
    override val label: Int = R.string.news_label
    override val route: String = news
}

object Expands : Destinations {
    override val icon: ImageVector = bulleted
    override val label: Int = R.string.expand_label
    override val route: String = expandsRoute
}

internal val cryptoDestinations = listOf(Coins, FocusCoin, FavoriteCoins, News, Expands)
internal val navigationBottomBarPoints = listOf(Coins, FavoriteCoins, News)
internal val navigationRailPoints = listOf(Coins, FavoriteCoins, News, Expands)
