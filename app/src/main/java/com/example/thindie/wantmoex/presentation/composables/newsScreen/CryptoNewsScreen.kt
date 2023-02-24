package com.example.thindie.wantmoex.presentation.composables.newsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.NewsViewModel
import com.example.thindie.wantmoex.presentation.composables.util.*
import com.example.thindie.wantmoex.route.actionGoBrowse
import com.example.thindie.wantmoex.route.actionShare


@Composable
fun CryptoNewsScreen(
    tagList: List<String>,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    newsViewModel.onLoadNews(tagList)
    val newsState = newsViewModel.uiNewsState.collectAsStateWithLifecycle()
    LoadingContent(isLoading = newsState.value.isLoading,
        isEmpty = newsState.value.news.isEmpty(),
        emptyContent = { ColorShimmer(false) },
        onRefresh = { newsViewModel.onLoadNews(tagList) }) {
        LazyColumn() {
            items(newsState.value.news) {
                Spacer(
                    modifier = Modifier
                        .surfaceColor()
                        .size(60.dp)
                )
                CryptoNewsElement(article = it)
            }
        }
    }
}

@Composable
fun CryptoNewsElement(article: News) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .surfaceColor()
            .fillMaxWidth()
            .eightStartPadding()
            .eightEndPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 250.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.eightEndPadding()) {
                IconButton(onClick = {
                    actionShare(
                        contentUri = article.url,
                        context = context,
                        uri = article.url,
                        title = article.title
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint.copy(0.5f)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .surfaceColor()
                        .padding(all = 10.dp)
                )
                IconButton(onClick = {
                    actionGoBrowse(article.url, context)
                }) {
                    Icon(
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint.copy(0.5f)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .surfaceColor()
                    .eightStartPadding()
                    .eightEndPadding()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = article.imageUrl, contentScale = ContentScale.Fit
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(250.dp)
                        .clip(ShapeDefaults.ExtraLarge.copy(CornerSize(30.dp)))
                )
            }
        }

        Spacer(
            modifier = Modifier
                .surfaceColor()
                .padding(all = 10.dp)
        )

        article.title.HeadLineNews(
            Modifier
                .eightEndPadding()
                .eightStartPadding()
        )
        Spacer(
            modifier = Modifier
                .surfaceColor()
                .padding(all = 10.dp)
        )
        Divider(thickness = Dp.Hairline)
        article.body.News(
            Modifier
                .eightEndPadding()
                .eightStartPadding()
        )
        Divider()
        article.tags.Mini(
            Modifier
                .eightEndPadding()
                .eightStartPadding()
        )
    }
}