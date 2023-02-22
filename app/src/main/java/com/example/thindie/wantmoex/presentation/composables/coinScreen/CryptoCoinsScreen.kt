package com.example.thindie.wantmoex.presentation.composables.coinScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.composables.CoinInFocus
import com.example.thindie.wantmoex.presentation.composables.Coins
import com.example.thindie.wantmoex.presentation.composables.util.*
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_onError
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_surfaceTint

@Composable
fun CryptoCoinsScreen(
    onClickCoin: (String, String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onRefresh: () -> Unit,
    isLoading: Boolean,
    coinList: List<CoinUIModel>,
) {
    LoadingContent(
        isLoading = isLoading,
        isEmpty = coinList.isEmpty(),
        emptyContent = { ColorShimmer() },
        onRefresh = { onRefresh() }) {

        LazyColumn(
            modifier = Modifier
                .surfaceColor()
                .fillMaxSize()
        )
        {
            items(coinList) { coinItem ->
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
                stringResource(R.string.coin_id_ticker, model.fromSymbol).HeadLine()
                stringResource(R.string.coin_last_market, model.lastMarket).Medium()
                stringResource(R.string.coin_last_updtated, model.lastUpdate.toTime())
                    .plus(stringResource(id = R.string.dot))
                    .Mini()

                Spacer(modifier = Modifier.eightStartPadding())
            }
            Column(modifier = Modifier.eightEndPadding()) {
                stringResource(id = R.string.price, model.price).HeadLine()
                Text(
                    text = model.percentDelta,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (model.isGrowing) Color.Green else Color.Red
                )
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
fun CryptoCoinDetailScreen(coin: CoinUIModel, onClickBack: (String) -> Unit) {

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .surfaceColor()
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .surfaceColor()
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 16.dp)
        ) {
            IconButton(onClick = { onClickBack(Coins.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(80.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Column(modifier = Modifier.halfScreenColumns()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = coin.imageUrl),
                        contentDescription = ""
                    )
                }
                Column(modifier = Modifier.halfScreenColumns()) {
                    stringResource(
                        R.string.coin_last_updtated,
                        coin.lastUpdate.toTime()
                    ).Mini()
                    stringResource(R.string.price, coin.price).HeadLine()
                    stringResource(R.string.coin_open_day, coin.openDay).Medium()
                    stringResource(R.string.coin_last_market, coin.market).Medium()
                }
                Column() {
                    if (coin.isGrowing) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = "",
                            tint = Color.Green
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Default.ArrowDownward,
                            contentDescription = "",
                            tint = Color.Red
                        )
                    }

                    Text(
                        text = coin.percentDelta,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (coin.isGrowing) Color.Green else Color.Red
                    )
                }

            }
            Divider(
                thickness = Dp.Hairline,
                modifier = Modifier
                    .onSurfaceColor()
                    .padding(bottom = 1.dp)
            )

            Row(
                modifier = Modifier
                    .eightStartPadding()
                    .padding(top = 20.dp)
            ) {
                stringResource(R.string.coin_id_ticker, coin.fromSymbol).HeadLine()
                Spacer(modifier = Modifier.weight(0.4f))
                stringResource(id = R.string.dot).Mini()
                stringResource(R.string.coin_low_today, coin.lowDay).Body()
                stringResource(id = R.string.dot).Mini()
                stringResource(R.string.coin_high_today, coin.highDay).Body()
            }

        }
    }
}

fun addOrDeleteToFavorites(model: CoinUIModel, del: (String) -> Unit, add: (String) -> Unit) {
    if (model.isFavorite) del(model.fromSymbol) else add(
        model.fromSymbol
    )
}

