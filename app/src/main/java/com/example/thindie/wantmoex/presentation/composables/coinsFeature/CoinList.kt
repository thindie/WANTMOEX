package com.example.thindie.wantmoex.presentation.composables.util

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.composables.coinsFeature.CoinListElement


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