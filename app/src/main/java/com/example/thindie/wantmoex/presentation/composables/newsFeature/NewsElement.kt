package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.theme.Shapes
import com.example.thindie.wantmoex.route.actionGoBrowse
import com.example.thindie.wantmoex.route.actionShare

private const val DOT = " • "
private const val GO_SURF_THIS_ARTICLE_WEB = "Go see more in Web"

@Composable
fun NewsElement(news: News) {
    val context = LocalContext.current


    val paddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = 8.dp,
        bottom = 8.dp
    )
    val startPadding = PaddingValues(start = 16.dp)
    val modifier = Modifier

    OutlinedCard(
        modifier = modifier.padding(paddingValues),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.inversePrimary)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp)
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(model = news.imageUrl),
                    contentScale = ContentScale.Fit,
                    contentDescription = "new",
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(Shapes.extraLarge)
                )
            }
        }
        Column(
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
                ) {
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(startPadding)
                    )
                }

            }

            Row(
                modifier = Modifier
                    .padding(startPadding)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { actionGoBrowse(news.url, context) },
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    Icon(imageVector = Icons.Default.Web, contentDescription = "More")
                }
                Text(
                    text = DOT,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = GO_SURF_THIS_ARTICLE_WEB,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.weight(0.2f))
                IconButton(onClick = {
                    actionShare(
                        news.url,
                        news.title,
                        news.imageUrl,
                        context
                    )
                }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "actionShare")
                }
            }

        }

    }

    Column(
        modifier = modifier.padding(paddingValues)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            Text(
                text = news.categories,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(startPadding)
            )
        }
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
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
            ) {
                Text(
                    text = news.body,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(startPadding),
                    textAlign = TextAlign.Justify
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






