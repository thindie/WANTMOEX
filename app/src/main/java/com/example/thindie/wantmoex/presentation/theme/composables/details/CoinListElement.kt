package com.example.thindie.wantmoex.presentation.theme.composables.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.domain.entities.Coin

@Composable

fun CoinListElement(coin: Coin, onClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .clickable { onClick(coin.fromSymbol) }
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

                    }

                }
            }
            Divider(thickness = Dp.Hairline)
        }

    }
}