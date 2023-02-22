package com.example.thindie.wantmoex.presentation.composables.newsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.NewsViewModel
import com.example.thindie.wantmoex.presentation.composables.util.*

@Composable

fun CryptoNewsScreen(
    tagList: List<String>,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    newsViewModel.onLoadNews(tagList)
    val newsState = newsViewModel.uiNewsState.collectAsStateWithLifecycle()
    LoadingContent(
        isLoading = newsState.value.isLoading,
        isEmpty = newsState.value.news.isEmpty(),
        emptyContent = { /*TODO*/ },
        onRefresh = { newsViewModel.onLoadNews(tagList) }) {
        LazyColumn() {
            items(newsState.value.news) {
                CryptoNewsElement(article = it)
            }
        }
    }
}

@Composable
fun CryptoNewsElement(article: News) {
    Column(
        modifier = Modifier
            .surfaceColor()
            .fillMaxWidth()
            .eightStartPadding()
            .eightEndPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(rememberAsyncImagePainter(model = article.imageUrl), contentDescription = "")
        article.title.HeadLine()
        Spacer(
            modifier = Modifier
                .onSurfaceColor()
                .padding(all = 10.dp)
        )
        Divider(thickness = Dp.Hairline)
        article.body.Body()
        Divider()
        article.tags.Mini()
    }
}