package com.example.thindie.wantmoex.presentation.composables.coinScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.CoinInFocus
import com.example.thindie.wantmoex.presentation.composables.util.*
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_onError
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_surfaceTint

@Composable
fun CryptoCoinsScreen(
    onClickCoin: (String, String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onRefresh: () -> Unit,
    state: CoinViewModel.CoinUIState,
) {
    LoadingContent(
        isLoading = state.isLoading,
        isEmpty = state.coinsList.isEmpty(),
        emptyContent = { ColorShimmer() },
        onRefresh = { onRefresh() }) {
        LazyColumn(modifier = Modifier.surfaceColor().fillMaxSize())
        {
            items(state.coinsList) { coinItem ->
                CoinListElement(
                    model = coinItem,
                    isReveal = coinItem.isShowExpand,
                    onFavoritesAdded = onFavoritesAdded,
                    onFavoritesDeleted = onFavoritesDeleted,
                    onClickCoin = { onClickCoin(CoinInFocus.route, coinItem.fromSymbol) }
                )
            }
        }
    }
}


@Composable
fun CoinListElement(
    model: CoinUIModel,
    isReveal: Boolean,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onClickCoin: (String) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(model.isFavorite) }


    Surface(color = MaterialTheme.colorScheme.surface) {
        Divider(Modifier.onSurfaceColor(), thickness = Dp.Hairline)
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(all = 10.dp)
                .clickable { onClickCoin(model.fromSymbol) }
        ) {
            Column() {
                Image(
                    painter = rememberAsyncImagePainter(model = model.imageUrl),
                    contentDescription = ""
                )
            }
            Column(modifier = Modifier.eightStartPadding()) {
                onText(res = R.string.coin_id_ticker, text = model.fromSymbol).HeadLine()
                onText(res = R.string.coin_last_market, text = model.lastMarket).Medium()
                onText(res = R.string.coin_last_updtated, text = model.lastUpdate.toTime())
                    .plus(stringResource(id = R.string.dot))
                    .Mini()

                Spacer(modifier = Modifier.eightStartPadding())
            }
            Column(modifier = Modifier.eightEndPadding()) {
                String.format(stringResource(id = R.string.price), model.price).HeadLine()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                if (isReveal) {
                    IconButton(onClick = {
                        isFavorite = !isFavorite; addOrDeleteToFavorites(
                        model,
                        onFavoritesDeleted,
                        onFavoritesAdded
                    )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (isFavorite) md_theme_dark_onError else md_theme_dark_surfaceTint
                        )
                    }
                }
            }

        }
    }

}


@Composable
fun CryptoCoinDetailScreen(coin: CoinUIModel) {
    Text(text = coin.fromSymbol)
}

fun addOrDeleteToFavorites(model: CoinUIModel, del: (String) -> Unit, add: (String) -> Unit) {
    if (model.isFavorite) del(model.fromSymbol) else add(
        model.fromSymbol
    )
}

