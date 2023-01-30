package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.presentation.CoinUIModel

private const val USD = " $"

@Composable
fun CoinListElement(
    coin: CoinUIModel,
    showFavoriteSymbol: Boolean,
    added: (String) -> Unit,
    deleted: (String) -> Unit,
    onClick: (String) -> Unit
) {

    var favoriteState by rememberSaveable { mutableStateOf(coin.isFavorite) }


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
                    painter = rememberAsyncImagePainter(model = coin.imageUrl),
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
                            text = coin.price.plus(USD),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = " • ".plus(coin.lastMarket),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(modifier = Modifier.weight(0.9f))
                        if (showFavoriteSymbol) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                IconButton(
                                    onClick = {
                                        if (!favoriteState) {
                                            added(coin.fromSymbol)
                                        } else deleted(coin.fromSymbol)

                                        favoriteState = !favoriteState
                                    },

                                    modifier = Modifier.padding(bottom = 15.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "",
                                        tint = if (favoriteState) MaterialTheme.colorScheme.inversePrimary
                                        else MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                }
                            }

                        }

                    }

                }
            }
            Divider(thickness = Dp.Hairline)
        }

    }
}