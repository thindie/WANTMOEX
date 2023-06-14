package com.example.core.presentation.uicoin.coinscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core.designelements.R
import com.example.core.designelements.components.screenelements.asBarFiller
import com.example.core.designelements.components.screenelements.asButtonFiller
import com.example.core.designelements.components.screenelements.buttonsbar.OutlinedButtonsBar
import com.example.core.designelements.components.screenelements.pressicon.PressIcon
import com.example.core.designelements.components.util.HeadLineText
import com.example.core.designelements.components.util.MediumText
import com.example.core.designelements.components.util.MiniText
import com.example.core.designelements.components.util.timeInMillisToString
import com.example.core.designelements.cryptoroutes.CryptoRoutes.coinsRoute
import com.example.core.designelements.icons.ShortHandedIcons.arrowBack
import com.example.core.designelements.icons.ShortHandedIcons.downArrow
import com.example.core.designelements.icons.ShortHandedIcons.upArrow
import com.example.core.domain.domaincoin.entity.Coin

@Suppress("LongParameterList")
@Composable
internal fun CoinItemScreen(
    modifier: Modifier = Modifier,
    coin: Coin,
    isWideScreen: Boolean,
    onWatchNews: (String) -> Unit,
    onAddTicker: (String) -> Unit,
    onClickBack: (String) -> Unit,
) {
    if (isWideScreen) {
        CoinItemWideScreen(
            coin = coin,
            modifier = modifier,
            onWatchNews = onWatchNews,
            onClickBack = onClickBack,
            onAddTicker = onAddTicker
        )
    } else {
        CoinItemPortraitScreen(
            coin = coin,
            modifier = modifier,
            onWatchNews = onWatchNews,
            onClickBack = onClickBack,
            onAddTicker = onAddTicker
        )
    }
}

@Composable
internal fun CoinItemWideScreen(
    modifier: Modifier = Modifier,
    onWatchNews: (String) -> Unit,
    onAddTicker: (String) -> Unit,
    onClickBack: (String) -> Unit,
    coin: Coin,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 8.dp)
    ) {
        Column(modifier.fillMaxWidth(0.4f)) {
            CoinItemHead(
                onWatchNews = onWatchNews,
                onAddTicker = onAddTicker,
                onClickBack = onClickBack,
                ticker = coin.fromSymbol
            )
            CoinTicker(ticker = coin.fromSymbol)
        }
        Column(modifier.fillMaxWidth(0.8f)) {
            CoinItemBody(coin = coin, modifier = modifier, isWideScreen = true)
        }
    }
}

@Composable
internal fun CoinItemPortraitScreen(
    modifier: Modifier = Modifier,
    onWatchNews: (String) -> Unit,
    onAddTicker: (String) -> Unit,
    onClickBack: (String) -> Unit,
    coin: Coin,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        CoinItemHead(
            modifier = modifier,
            onWatchNews = onWatchNews,
            onAddTicker = onAddTicker,
            onClickBack = onClickBack,
            ticker = coin.fromSymbol
        )
        CoinTicker(ticker = coin.fromSymbol, modifier = Modifier.height(100.dp))
        CoinItemBody(coin = coin, modifier = modifier.padding(top = 100.dp), isWideScreen = false)
        CoinItemCellar(
            lowDay = coin.lowDay,
            highDay = coin.highDay,
            modifier = modifier,
        )
    }
}

@Composable
internal fun CoinItemHead(
    modifier: Modifier = Modifier,
    onWatchNews: (String) -> Unit,
    onAddTicker: (String) -> Unit,
    onClickBack: (String) -> Unit,
    ticker: String,
) {
    Row(modifier.fillMaxWidth()) {
        PressIcon(barFiller = asBarFiller(arrowBack, coinsRoute), onPress = onClickBack)
        Spacer(modifier = modifier.height(30.dp))
        OutlinedButtonsBar(
            list = listOf(
                asButtonFiller(
                    label = stringResource(id = R.string.favorite_label),
                ) { onAddTicker(ticker) },
                asButtonFiller(
                    label = stringResource(id = R.string.read_coin_news),
                ) { onWatchNews(ticker) }
            )
        )
    }
}

@Composable
internal fun CoinItemBody(modifier: Modifier = Modifier, coin: Coin, isWideScreen: Boolean) {
    Column(
        modifier = modifier
            .heightIn(min = 200.dp, max = 500.dp)
            .padding(bottom = 10.dp, end = 12.dp),
    ) {
        LazyRow() {
            item {
                Image(
                    painter = rememberAsyncImagePainter(model = coin.imageUrl),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .padding(5.dp)
                )
            }
            item {
                CoinBodyText(coin = coin)
            }
        }
        CoinBodyPriceColoredPercent(coin = coin, isWideScreen = isWideScreen)
    }
}

@Composable
internal fun CoinItemCellar(modifier: Modifier = Modifier, lowDay: String, highDay: String) {
    Column {
        Divider(
            thickness = Dp.Hairline,
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.onSurface)
                .padding(bottom = 1.dp)
        )

        Row(
            modifier = modifier
                .padding(start = 8.dp)
                .padding(top = 20.dp)
        ) {
            stringResource(R.string.coin_low_today, lowDay).HeadLineText()
            Spacer(modifier = modifier.padding(all = 20.dp))
            stringResource(R.string.coin_high_today, highDay).HeadLineText()
        }
    }
}

@Composable
internal fun CoinBodyPriceColoredPercent(
    modifier: Modifier = Modifier,
    coin: Coin,
    isWideScreen: Boolean
) {
    Column(
        verticalArrangement = if (isWideScreen) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize(0.6f)
            .padding(start = 8.dp)
    ) {
        val isZeroProgress =
            coin.percentDelta
                .trim()
                .lowercase() == "+0.0%" ||
                    coin.percentDelta
                        .trim()
                        .lowercase() == "-0.0%"
        val percentModified =
            if (isZeroProgress) coin.percentDelta.replaceFirstChar { "" } else coin.percentDelta
        Row(
            verticalAlignment = if (isWideScreen) Alignment.CenterVertically else Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = percentModified,
                style = MaterialTheme.typography.displaySmall,
                color = if (coin.isGrowing) {
                    if (isZeroProgress) Color.Gray else Color.Green
                } else if (isZeroProgress) Color.Gray else Color.Red
            )
            if (!isZeroProgress) {
                Icon(
                    imageVector = if (coin.isGrowing) upArrow else downArrow,
                    contentDescription = "",
                    tint = if (coin.isGrowing) Color.Green else Color.Red
                )
            }
        }
    }
}

@Composable
internal fun CoinBodyText(modifier: Modifier = Modifier, coin: Coin) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        stringResource(
            R.string.coin_last_upd,
            coin.lastUpdate.timeInMillisToString()
        ).MiniText()
        stringResource(R.string.price, coin.price).HeadLineText()
        stringResource(R.string.coin_open_day, coin.openDay).MediumText()
        stringResource(R.string.coin_last_market, coin.market).MediumText()
    }
}

@Composable
internal fun CoinTicker(modifier: Modifier = Modifier, ticker: String) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = ticker, style = MaterialTheme.typography.displaySmall)
    }
}
