package com.example.thindie.wantmoex.presentation.composables.coinScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.CoinViewModel
import com.example.thindie.wantmoex.presentation.composables.CoinInFocus
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_onError
import com.example.thindie.wantmoex.presentation.theme.md_theme_dark_surfaceTint

@Composable
fun CryptoCoinsScreen(
    onClickCoin: (String, String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    state: CoinViewModel.CoinUIState,
) {
    LazyColumn(
    ) {
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


@Composable
fun CoinListElement(
    model: CoinUIModel,
    isReveal: Boolean,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onClickCoin: (String) -> Unit,
) {
    var color by remember { mutableStateOf(model.isFavorite) }
    Divider()
    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClickCoin(model.fromSymbol) }
    ) {
        Column() {
            Image(
                painter = rememberAsyncImagePainter(model = model.imageUrl),
                contentDescription = ""
            )
        }
        Column() {
            Text(model.fromSymbol)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            if (isReveal) {
                IconButton(onClick = {
                    color = !color; addOrDeleteToFavorites(
                    model,
                    onFavoritesDeleted,
                    onFavoritesAdded
                )
                }) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (color) md_theme_dark_onError else md_theme_dark_surfaceTint
                    )
                }
            }
        }

    }
}


@Composable
fun CryptoCoinDetailScreen(coin: CoinUIModel) {

    Text(text = coin.fromSymbol)

}

fun addOrDeleteToFavorites(model: CoinUIModel, a: (String) -> Unit, b: (String) -> Unit) {
    if (model.isFavorite) a(model.fromSymbol) else b(
        model.fromSymbol
    )
}