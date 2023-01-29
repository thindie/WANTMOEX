package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.data.mappers.getHowLongAgo
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased


private const val UPDATED = "Last updated"
private const val ON_MARKET = "Last trade on"
private const val TITLE = "Coin: "
private const val PRICE = "Price"
private const val TODAY_HIGHEST_PRICE = "Today's highest"
private const val TODAY_LOWEST_PRICE = "Today's lowest"
private const val DOT = " • "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailsScreen(
    coin: CoinUIModel,
    onClickBack: () -> Unit,
) {

    val title = remember { mutableStateOf(TITLE) }

    LaunchedEffect(key1 = true) {
        animateTextByDotsOnStateBased(title.value, title)
    }

    Scaffold(
        bottomBar = { CoinDetailsBottomBar(onBack = { onClickBack() }) },
        topBar = { CoinTopAppBar(title = title.value, onClick = { }) }

    ) {
        title.value = TITLE
        CoinDetailScreenContent(coin = coin, modifier = Modifier.padding(it))
    }
}

@Composable
private fun CoinDetailScreenContent(coin: CoinUIModel, modifier: Modifier) {

    val paddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = 8.dp,
        bottom = 8.dp
    )
    val startPadding = PaddingValues(start = 16.dp)

    Column(modifier = modifier) {
        OutlinedCard(
            modifier = modifier.padding(paddingValues)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = coin.imageUrl),
                    contentDescription = "coin image"
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = coin.fromSymbol,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(startPadding)
                    )
                    Text(
                        text = UPDATED.plus(DOT).plus(getHowLongAgo(coin.lastUpdate)),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(startPadding)
                    )
                    Text(
                        text = ON_MARKET.plus(DOT).plus(coin.lastMarket),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(startPadding)
                    )
                }

            }

        }
        OutlinedCard(
            modifier = modifier.padding(paddingValues)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(startPadding)
                ) {
                    Icon(
                        imageVector = Icons.Default.Payments,
                        contentDescription = "decor",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
                ) {
                    Text(
                        text = PRICE.plus(DOT).plus(coin.price),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(startPadding)
                    )
                    Text(
                        text = TODAY_HIGHEST_PRICE.plus(DOT).plus(coin.highDay),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(startPadding)
                    )
                    Text(
                        text = TODAY_LOWEST_PRICE.plus(DOT).plus(coin.lowDay),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(startPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun CoinDetailsBottomBar(
    onBack: () -> Unit,
) {
    val paddingValues = PaddingValues(start = 4.dp, end = 12.dp, top = 12.dp)
    val showSaveFavorites = remember { mutableStateOf(false) }

    BottomAppBar(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        }
    }
}

