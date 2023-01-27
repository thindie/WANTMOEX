package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.domain.entities.News


private const val UPDATED = "Last updated"
private const val ON_MARKET = "Last trade on"

private const val PRICE = "Price"
private const val TODAY_HIGHEST_PRICE = "Today's highest"
private const val TODAY_LOWEST_PRICE = "Today's lowest"
private const val DOT = " • "


@Composable
fun NewsElement(news: News, onClickBack: () -> Unit) {

    val paddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = 8.dp,
        bottom = 8.dp
    )
    val startPadding = PaddingValues(start = 16.dp)

    val modifier = Modifier
    Column(modifier = modifier) {
        ElevatedCard(
            modifier = modifier.padding(paddingValues)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = news.imageUrl),
                contentDescription = "coin image"
            )
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
                ) {
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(startPadding)
                    )
                    /* Text(
                         text = news.body,
                         style = MaterialTheme.typography.labelLarge,
                         modifier = Modifier.padding(startPadding)
                     )
                     Text(
                         text = news.tags,
                         style = MaterialTheme.typography.labelSmall,
                         color = MaterialTheme.colorScheme.secondary,
                         modifier = Modifier.padding(startPadding)
                     )*/
                }

            }
            IconButton(
                onClick = { onClickBack(); }, modifier = Modifier
                    .padding(paddingValues)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }

        }
        ElevatedCard(
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
                        text = news.body,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(startPadding)
                    )
                    Text(
                        text = news.tags,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(startPadding)
                    )
                }
            }
        }
    }
}





