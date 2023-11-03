package com.example.core.presentation.uicoinlist.coinslistscreen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberCoinListScreen(
    listState: LazyListState,
    isWideScreen: Boolean,
    isAdditionalUIsectionsExpanded: Boolean,
    isShowFavorite: Boolean,
): CoinListScreenState {
    return remember(listState, isWideScreen, isAdditionalUIsectionsExpanded, isShowFavorite) {
        CoinListScreenState(
            listState = listState,
            isWideScreen = isWideScreen,
            isExpanded = isAdditionalUIsectionsExpanded,
            isShowFavorite = isShowFavorite
        )
    }
}

@Stable
class CoinListScreenState(
    private val listState: LazyListState,
    private val isWideScreen: Boolean,
    private val isExpanded: Boolean,
    private val isShowFavorite: Boolean,
) {

    internal var onActivateSort: ((Boolean) -> Unit) = { (isShowFavorites) }
    val askListScreenUpdate: () -> Unit = { onActivateSort(isShowFavorites) }

    val coinListState
        get() = listState

    val isLandscape
        @Composable get() = isWideScreen

    val isExpandedSection
        @Composable get() = isExpanded

    val isShowFavorites
        get() = isShowFavorite
}
