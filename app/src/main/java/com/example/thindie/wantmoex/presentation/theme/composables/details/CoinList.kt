package com.example.thindie.wantmoex.presentation.theme.composables.details

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin


@Composable

fun CoinList(modifier: Modifier, onClickElement: (String) -> Unit, list: List<Coin>) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(list) { coinItem ->
            CoinListElement(coin = coinItem, onClickElement)
        }
    }
}