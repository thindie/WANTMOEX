package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin


@Composable

fun CoinList(
    modifier: Modifier,
    onClickElement: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    list: List<Coin>,
    listOfFavorites: List<String>?
) {
    val state = rememberLazyListState()

    if (listOfFavorites == null) {
        LazyColumn(
            modifier = modifier,
            state = state
        ) {
            items(list) { coinItem ->
                CoinListElement(
                    coin = coinItem,
                    false,
                    showFavoriteSymbol = false,
                    onFavoritesAdded,
                    onFavoritesDeleted,
                    onClickElement
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier,
            state = state
        ) {
            items(list) { coinItem ->
                if (listOfFavorites.contains(coinItem.fromSymbol)) {
                    CoinListElement(
                        coin = coinItem,
                        true,
                        showFavoriteSymbol = true,
                        onFavoritesAdded,
                        onFavoritesDeleted,
                        onClickElement
                    )
                } else {
                    CoinListElement(
                        coin = coinItem,
                        false,
                        showFavoriteSymbol = true,
                        onFavoritesAdded,
                        onFavoritesDeleted,
                        onClickElement
                    )
                }

            }
        }
    }
 }


