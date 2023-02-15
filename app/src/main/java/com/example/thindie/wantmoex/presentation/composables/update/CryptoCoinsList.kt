package com.example.thindie.wantmoex.presentation.composables.update

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.presentation.CoinUIModel

@Composable
fun CryptoCoinsList(
    modifier: Modifier,
    revealedFavoritesSection: Boolean,
    onClickCoin: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    list: List<CoinUIModel>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(list) { coinItem ->

        }
    }
}