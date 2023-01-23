package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.theme.Shapes

@Composable
fun ShareItem(coin: Coin, onClick: (Coin) -> Unit) {

    Card(
        shape = Shapes.extraLarge,
        modifier = Modifier
            .padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)
            .fillMaxWidth()
            .height(85.dp)
    ) {
        Row() {
            Column(modifier = Modifier.padding(start = 20.dp)) {

                Text(
                    text = coin.fromSymbol,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 60.dp,
                    modifier = Modifier
                        .width(120.dp)
                        .clip(shape = Shapes.extraLarge)
                ) {
                    Text(
                        text = coin.fromSymbol, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                }

                Text(
                    text = coin.fromSymbol, style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.clickable { onClick(coin) }

                )
            }
            Spacer(modifier = Modifier.weight(0.3f))
            Image(
                painter = rememberAsyncImagePainter(""),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.lighting(
                    MaterialTheme.colorScheme.secondaryContainer,
                    Color.Transparent
                ),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(CenterVertically)
                    .size(72.dp)
                    .clip(CircleShape)

            )
            // Spacer(modifier = Modifier.weight(0.1f))
        }

    }
}


@Composable
fun ShareItemExpanded(list: List<Coin>, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxSize()
    ) {
        Card(
            shape = Shapes.extraLarge,
            modifier = Modifier
                .padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)
                .fillMaxWidth()
                .height(380.dp)
        ) {
            Row() {
                Column(modifier = Modifier.padding(start = 20.dp)) {


                    Text(
                        text = list[0].fromSymbol, style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.clickable { onClick() }

                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
                Image(
                    painter = rememberAsyncImagePainter(""),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.lighting(
                        MaterialTheme.colorScheme.secondaryContainer,
                        Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(end = 20.dp, top = 10.dp)
                        .align(CenterVertically)
                        .size(72.dp)
                        .clip(CircleShape)

                )
                // Spacer(modifier = Modifier.weight(0.1f))
            }
            list.forEach {
                val modifier: Modifier =
                    Modifier.padding(top = 10.dp, bottom = 10.dp, start = 12.dp)
                val style = MaterialTheme.typography.titleSmall

                Row(modifier = modifier) {
                    Column(modifier = modifier) {
                        Text(
                            text = it.fromSymbol.replace(
                                "\"", " "
                            ).replace("-", " ").trim(),
                            style = style
                        )

                    }
                    Column(modifier = modifier) {
                        Text(
                            text = "- price was:",
                            style = style
                        )
                    }

                    Column(modifier = modifier) {
                        Text(
                            text = it.fromSymbol,
                            style = style
                        )
                    }

                }

            }
        }

    }
}
