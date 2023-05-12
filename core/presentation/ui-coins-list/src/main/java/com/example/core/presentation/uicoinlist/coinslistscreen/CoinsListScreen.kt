package com.example.core.presentation.uicoinlist.coinslistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core.designelements.R
import com.example.core.designelements.components.screenelements.asBarFiller
import com.example.core.designelements.components.screenelements.monosystemscreens.LoadingContent
import com.example.core.designelements.components.screenelements.monosystemscreens.ShimmerState
import com.example.core.designelements.components.screenelements.monosystemscreens.rememberShimmerStateScreenDecoration
import com.example.core.designelements.components.screenelements.pressicon.PressIcon
import com.example.core.designelements.components.util.BodyText
import com.example.core.designelements.components.util.HeadLineText
import com.example.core.designelements.components.util.MediumText
import com.example.core.designelements.components.util.MiniText
import com.example.core.designelements.components.util.timeInMillisToString
import com.example.core.designelements.cryptoroutes.CryptoRoutes
import com.example.core.designelements.icons.ShortHandedIcons
import com.example.core.domain.domaincoinslist.entity.Coin

@Suppress("LongParameterList")
@Composable
fun CoinsListScreen(
    loadingContentDecorator: ShimmerState =
        rememberShimmerStateScreenDecoration(CryptoRoutes.coinsRoute),
    lazyListState: LazyListState,
    onRefresh: () -> Unit,
    isLoading: Boolean,
    coinsList: List<Coin>,
    isWideScreen: Boolean,
    isExpanded: Boolean,
    onClickFavoriteCoin: (String) -> Unit,
    onClickCoin: (String) -> Unit,
) {
    LoadingContent(
        isLoading = isLoading,
        isEmpty = coinsList.isEmpty(),
        emptyContent = { loadingContentDecorator.Screen },
        onRefresh = onRefresh
    ) {
        if (isWideScreen) {
            CoinsListWideScreen(
                coins = coinsList,
                isExpanded = isExpanded,
                onClickCoin = onClickCoin,
                onClickFavoriteCoin = onClickFavoriteCoin,
                lazyListState = lazyListState
            )
        } else {
            CoinsListPortraitScreen(
                coins = coinsList,
                onClickFavoriteCoin = onClickFavoriteCoin,
                onClickCoin = onClickCoin,
                isExpanded = isExpanded,
                lazyListState = lazyListState
            )
        }
    }
}

@Suppress("LongParameterList")
@Composable
internal fun CoinsListWideScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    coins: List<Coin>,
    isExpanded: Boolean,
    onClickFavoriteCoin: (String) -> Unit,
    onClickCoin: (String) -> Unit,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(start = 8.dp, end = 20.dp, top = 2.dp, bottom = 2.dp)
    ) {
        items(coins, key = { it.fromSymbol }) { coin ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 2.dp)
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { onClickCoin(coin.fromSymbol) }
                        .padding(end = 20.dp)
                ) {
                    ImageSection(url = coin.imageUrl)
                    TickerSectionWide(
                        ticker = coin.fromSymbol,
                        lastTrade = coin.lastMarket,
                        lastUpdate = coin.lastUpdate
                    )
                    Spacer(modifier = modifier.weight(1f))
                    PriceSection(
                        price = coin.price,
                        percent = coin.percentDelta,
                        isGrowing = coin.isGrowing
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp, end = 4.dp))
                    if (isExpanded) {
                        HeartsSection(
                            ticker = coin.fromSymbol,
                            isFavorite = coin.isFavorite,
                            onClickFavoriteCoin = onClickFavoriteCoin
                        )
                    }
                }
                Divider(thickness = Dp.Hairline)
            }
        }
    }
}

@Suppress("LongParameterList")
@Composable
internal fun CoinsListPortraitScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    coins: List<Coin>,
    isExpanded: Boolean,
    onClickFavoriteCoin: (String) -> Unit,
    onClickCoin: (String) -> Unit,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(start = 8.dp, end = 12.dp, top = 2.dp, bottom = 2.dp)
    ) {
        items(coins, key = { it.fromSymbol }) { coin ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { onClickCoin(coin.fromSymbol) }
                    .padding(end = 20.dp)
            ) {
                ImageSection(url = coin.imageUrl)
                TickerSectionPortrait(
                    ticker = coin.fromSymbol,
                    lastUpdate = coin.lastUpdate,
                    lastTrade = coin.lastMarket,
                )
                Spacer(modifier = modifier.weight(0.3f))
                PriceSection(
                    price = coin.price,
                    percent = coin.percentDelta,
                    isGrowing = coin.isGrowing,
                    modifier = Modifier.padding(start = 60.dp)
                )
                Spacer(modifier = modifier.padding(start = 4.dp, end = 4.dp))
                if (isExpanded) {
                    HeartsSection(
                        ticker = coin.fromSymbol,
                        onClickFavoriteCoin = onClickFavoriteCoin,
                        isFavorite = coin.isFavorite
                    )
                }
            }
        }
    }
}

@Composable
internal fun ImageSection(url: String, modifier: Modifier = Modifier) {
    Column() {
        Image(
            painter = rememberAsyncImagePainter(
                model = url,
                contentScale = ContentScale.Fit
            ),
            contentDescription = "",
            modifier = modifier.size(66.dp)
        )
    }
}

@Composable
internal fun PriceSection(
    price: String,
    percent: String,
    isGrowing: Boolean,
    modifier: Modifier = Modifier,
) {
    val isZeroProgress = percent.trim().lowercase() == "+0.0%" || percent == "-0.0%"
    val percentModified = if (isZeroProgress) percent.replaceFirstChar { "" } else percent
    Column(modifier = modifier) {
        stringResource(id = R.string.price, price).HeadLineText()
        Text(
            text = percentModified,
            style = MaterialTheme.typography.labelSmall,
            color = if (isGrowing) {
                if (isZeroProgress) Color.Gray else Color.Green
            } else if (isZeroProgress) Color.Gray else Color.Red
        )
    }
}

@Composable
internal fun TickerSectionPortrait(
    ticker: String,
    lastTrade: String,
    lastUpdate: Long,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        stringResource(R.string.coin_id_ticker, ticker).HeadLineText()
        stringResource(R.string.coin_last_market, lastTrade).MediumText()
        stringResource(
            R.string.coin_last_upd,
            lastUpdate.timeInMillisToString()
        ).MiniText()
    }
}

@Composable
internal fun TickerSectionWide(
    ticker: String,
    lastTrade: String,
    lastUpdate: Long,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        stringResource(
            R.string.coin_id_ticker,
            ticker
        ).HeadLineText(modifier = Modifier.padding(end = 20.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 2.dp)
        ) {
            stringResource(R.string.coin_last_market, lastTrade).BodyText()
            stringResource(
                R.string.coin_last_upd,
                lastUpdate.timeInMillisToString()
            ).MediumText()
        }
    }
}

@Composable
internal fun HeartsSection(
    ticker: String,
    isFavorite: Boolean,
    onClickFavoriteCoin: (String) -> Unit,
) {
    var isCoinFavorite by remember(isFavorite) {
        mutableStateOf(isFavorite)
    }
    PressIcon(
        barFiller = asBarFiller(imageVector = ShortHandedIcons.heart, route = ""),
        tint = if (isCoinFavorite) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(
                if (isCoinFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            ),
        onPress = { isCoinFavorite = !isCoinFavorite; onClickFavoriteCoin(ticker) }
    )
}
