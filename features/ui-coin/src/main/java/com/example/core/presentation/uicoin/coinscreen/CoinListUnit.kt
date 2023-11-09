package com.example.core.presentation.uicoin.coinscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.core.designelements.R
import com.example.core.designelements.components.screenelements.Shimmers
import com.example.core.designelements.theme.CryptoViewTheme
import com.example.thindie.domain.entities.Coin
import kotlinx.coroutines.delay
import kotlin.random.Random

private val scrollImitation = Brush.verticalGradient(
    listOf(
        Color.Black,
        Color.Transparent,
        Color.Black,
        Color.Black,
        Color.Transparent,
        Color.Transparent,
    )
)

@Composable
fun CoinListUnit(modifier: Modifier = Modifier, coin: Coin) {
    var isPictureReady by rememberSaveable { mutableStateOf(false) }
    var isPictureErrorLoading by rememberSaveable { mutableStateOf(false) }
    var shouldAnimateAdditionals by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        delay(Random.nextLong(from = 300, until = 1200))
        shouldAnimateAdditionals = true
    })

    Box(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
            .height(300.dp)
    ) {
        AnimatedVisibility(
            visible = shouldAnimateAdditionals,
            modifier = modifier
                .height(130.dp)
        ) {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        scrollImitation
                    )
                    .zIndex(1f)
            )
        }

        Column(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .background(MaterialTheme.colorScheme.background)
                .zIndex(2f)
                .clip(MaterialTheme.shapes.extraLarge)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (isPictureErrorLoading.not()) {
                    Shimmers(shouldBeShimmered = isPictureReady.not()) {
                        Image(
                            modifier = modifier
                                .clip(CircleShape)
                                .size(124.dp)
                                .zIndex(2f)
                                .then(it),
                            painter =
                            rememberAsyncImagePainter(
                                model = coin.imageUrl,
                                onSuccess = {
                                    isPictureReady = true
                                },
                                onError = {
                                    isPictureErrorLoading = true
                                    isPictureReady = true
                                }
                            ),
                            contentDescription = null,
                        )
                    }
                } else {
                    Icon(
                        modifier = modifier.size(70.dp),
                        painter = onPictureError,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                AnimatedVisibility(visible = isPictureReady) {
                    Row(
                        modifier = modifier
                            .padding(end = 12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = coin.fromSymbol,
                            style = MaterialTheme.typography.displaySmall.copy(
                                Color.White,
                                fontWeight = W800,
                            )
                        )

                        Text(
                            text = coin.price.coinPriceTrimOrValidate(),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.Green,
                                fontWeight = W800,
                            ),
                        )
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(0.7f)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            ) {
                AnimatedVisibility(visible = isPictureReady) {
                    Text(
                        text = coin.openDay.coinPriceTrimOrValidate(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(0.7f)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            ) {
                AnimatedVisibility(visible = isPictureReady) {
                    Text(
                        text = coin.changeDay.coinPriceTrimOrValidate(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}

private val onPictureError
    @Composable get() =
        painterResource(id = R.drawable.icon_error_image_loading)

private fun String.coinPriceTrimOrValidate(): String {
    return try {
        substring(startIndex = 0, endIndex = 8)
    } catch (e: StringIndexOutOfBoundsException) {
        this
    }
}

@Preview
@Composable
fun previewCoinListUnit() {
    CryptoViewTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF53674F))
        ) {
            CoinListUnit(
                coin = Coin(
                    type = "",
                    market = "",
                    fromSymbol = "BTC",
                    toSymbol = "",
                    flags = "",
                    price = "123",
                    lastUpdate = 0,
                    lastVolume = "",
                    lastVolumeTo = "",
                    lastTradeId = "",
                    volumeDay = "",
                    volumeDayTo = "",
                    volume24Hour = "",
                    volume24HourTo = "",
                    openDay = "",
                    highDay = "",
                    lowDay = "",
                    open24Hour = "",
                    high24Hour = "",
                    low24Hour = "",
                    lastMarket = "",
                    volumeHour = "",
                    volumeHourTo = "",
                    openHour = "",
                    highHour = "",
                    lowHour = "",
                    topTierVolume24Hour = "",
                    topTierVolume24HourTo = "",
                    change24Hour = "",
                    changePCT24Hour = "",
                    changeDay = "",
                    changePCTDay = "",
                    supply = "",
                    mktCap = "",
                    totalVolume24Hour = "",
                    totalVolume24HourTo = "",
                    totalTopTierVolume24Hour = "",
                    totalTopTierVolume24HourTo = "",
                    imageUrl = ""
                )
            )
        }
    }
}
