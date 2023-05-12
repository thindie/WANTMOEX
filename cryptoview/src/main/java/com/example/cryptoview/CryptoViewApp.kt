package com.example.cryptoview

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.example.core.designelements.components.screenelements.asBarFiller
import com.example.core.designelements.components.screenelements.bottomnavbar.CryptoCoinsBottomBar
import com.example.core.designelements.components.screenelements.iconrail.IconRail
import com.example.core.designelements.components.screenelements.topbar.scrollBehaviors
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinsRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.expandsRoute
import com.example.core.designelements.cryptoroutes.CryptoRoutes.news
import com.example.core.presentation.uicoin.coinscreen.coinItem
import com.example.core.presentation.uicoinlist.coinslistscreen.CoinListScreenState
import com.example.core.presentation.uicoinlist.coinslistscreen.coinsListScreen
import com.example.core.presentation.uicoinlist.coinslistscreen.favoriteCoinsScreen
import com.example.core.presentation.uicoinlist.coinslistscreen.rememberCoinListScreen
import com.example.core.presentation.uinewsfeed.newsscreen.newsScreen
import com.example.core.presentation.uisettings.SettingsBarState
import com.example.core.presentation.uisettings.SettingsScreenViewModel
import com.example.core.presentation.uisettings.UiSettingsBar
import com.example.core.presentation.uisettings.rememberSettingsBarState
import com.example.cryptoview.navigation.Expands
import com.example.cryptoview.navigation.navigationBottomBarPoints
import com.example.cryptoview.navigation.navigationRailPoints

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CryptoViewApp(
    isSystemDarkThemed: Boolean,
    isLandscapeOrExtraWide: Boolean,
    executeIntent: (Intent) -> Unit,
    settingsViewModel: SettingsScreenViewModel = viewModel(),
    topAppBarScrollBehavior: TopAppBarScrollBehavior = scrollBehaviors(),
    appState: CryptoViewState = rememberCryptoViewState(
        windowSizeClass = isLandscapeOrExtraWide,
        theme = isSystemDarkThemed
    ),
    topBarState: SettingsBarState = rememberSettingsBarState(
        route = appState.currentRoute,
        id = settingsViewModel.activatedTriggerID
    ),
    coinListScreenState: CoinListScreenState = rememberCoinListScreen(
        listState = rememberLazyListState(),
        isWideScreen = appState.isWideScreen,
        isAdditionalUIsectionsExpanded = settingsViewModel.isExpanded,
        isShowFavorite = false
    ),
    favoritesListScreenState: CoinListScreenState = rememberCoinListScreen(
        listState = rememberLazyListState(),
        isWideScreen = appState.isWideScreen,
        isAdditionalUIsectionsExpanded = settingsViewModel.isExpanded,
        isShowFavorite = true
    )
) {
    Scaffold(
        modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            UiSettingsBar(
                triggers = topBarState.currentTab,
                title = topBarState.title,
                scrollBehaviors = topAppBarScrollBehavior,
                onClick = { sortAction ->
                    settingsViewModel.applyTriggerSortAction(sortAction)
                    coinListScreenState.askListScreenUpdate()
                    favoritesListScreenState.askListScreenUpdate()
                }
            )
        },
        bottomBar = {
            if (appState.isShowTopBar) {
                CryptoCoinsBottomBar(
                    onSelectedDestination = appState::navigate,
                    actionsBarStart = navigationBottomBarPoints.map { destination ->
                        asBarFiller(destination.icon, destination.route)
                    },
                    actionBarEnd = asBarFiller(Expands.icon, Expands.route),
                    onOperateCurrentContent = settingsViewModel::onTriggerExpand
                )
            }
        }
    ) { scaffoldsPaddingValues ->
        WindowSizeDependableContent(
            modifier = Modifier.padding(scaffoldsPaddingValues),
            isLandscape = appState.isShowIconRail,
            onClick = appState::navigate,
            onClickExpand = settingsViewModel::onTriggerExpand
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = appState.startDestination
            ) {
                coinsListScreen(
                    state = coinListScreenState
                ) { appState.navigate(coinRoute) }
                coinItem(
                    isWide = appState.isWideScreen,
                    onBackPress = { appState.navigate(coinsRoute) }
                ) {
                    appState.navigate(news)
                }
                newsScreen(isWideScreen = appState.isWideScreen, executeIntent = executeIntent)
                favoriteCoinsScreen(
                    state = favoritesListScreenState,
                ) {
                    appState.navigate(coinRoute)
                }
            }
        }
    }
}

/**
 * [Scaffold] installation for better code readable ability
 * @see IconRail
 */

@Composable
internal fun WindowSizeDependableContent(
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    onClick: (String) -> Unit,
    onClickExpand: () -> Unit,
    navHost: @Composable () -> Unit,
) {
    Row(modifier = modifier) {
        if (isLandscape) {
            IconRail(
                components = navigationRailPoints.map { destinations ->
                    asBarFiller(destinations.icon, destinations.route)
                },
                onClick = { onClickAction ->
                    if (onClickAction == expandsRoute) {
                        onClickExpand()
                    } else {
                        val navigationDestination = onClickAction
                        onClick(navigationDestination)
                    }
                }
            )
        }
        navHost()
    }
}
