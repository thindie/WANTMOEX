package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.composables.util.cacheListAdd
import com.example.thindie.wantmoex.presentation.composables.util.cacheListRemove
import kotlinx.coroutines.delay

@Composable

fun CoinListElement(
    coin: Coin,
    isItFavorite: Boolean,
    showFavoriteSymbol: Boolean,
    onClick: (String) -> Unit
) {

    var imageState by remember { mutableStateOf(true) }
    var favoriteState by remember { mutableStateOf(isItFavorite) }

    LaunchedEffect(key1 = true) {
        delay(30)
        imageState = false
    }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)

    ) {
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 24.dp)
            ) {

                Image(
                    painter = if (imageState) painterResource(id = R.drawable.shape)
                    else rememberAsyncImagePainter(
                        model = coin.imageUrl
                    ),
                    contentDescription = "coin Image",
                    modifier = Modifier
                        .size(56.dp)
                        .clickable { onClick(coin.fromSymbol) }
                )

                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)

                ) {
                    Text(text = coin.fromSymbol, style = MaterialTheme.typography.headlineSmall)
                    Row {
                        Text(
                            text = coin.price.plus(" $"),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = " • ".plus(coin.lastMarket),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        if (showFavoriteSymbol) {
                            IconButton(
                                onClick = {
                                    if (favoriteState) {
                                        cacheListAdd(coin.fromSymbol)
                                    } else cacheListRemove(coin.fromSymbol)
                                    favoriteState = !favoriteState
                                },
                                modifier = Modifier.padding(bottom = 25.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "",
                                    tint = if (favoriteState) MaterialTheme.colorScheme.error
                                    else MaterialTheme.colorScheme.onTertiaryContainer
                                )

                            }
                        }

                    }

                }
            }
            Divider(thickness = Dp.Hairline)
        }

    }
}