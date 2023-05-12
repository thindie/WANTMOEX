package com.example.core.presentation.uinewsfeed.newsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core.common.consts.BROWSE
import com.example.core.common.consts.SHARE
import com.example.core.designelements.components.screenelements.asBarFiller
import com.example.core.designelements.components.screenelements.iconrail.IconRail
import com.example.core.designelements.components.screenelements.monosystemscreens.LoadingContent
import com.example.core.designelements.components.screenelements.monosystemscreens.ShimmerState
import com.example.core.designelements.components.screenelements.monosystemscreens.rememberShimmerStateScreenDecoration
import com.example.core.designelements.components.util.HeadLineTextNews
import com.example.core.designelements.components.util.MiniText
import com.example.core.designelements.components.util.NewsText
import com.example.core.designelements.cryptoroutes.CryptoRoutes
import com.example.core.designelements.icons.ShortHandedIcons.net
import com.example.core.designelements.icons.ShortHandedIcons.share
import com.example.core.domain.domainnewsfeed.entity.News
import com.example.core.presentation.uinewsfeed.viewmodel.NewsViewModel

/**
 * NewsScreen by several composition elements: [LoadingContent] which visualise 'loading or empty' states,
 * [IconRail] from 'design-elements' module, [ArticleHeadLine], [ArticleText]
 */
@Suppress("LongParameterList")
@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    newsState: State<NewsViewModel.NewsUiState>,
    isWideScreen: Boolean,
    loadingContentDecorator: ShimmerState = rememberShimmerStateScreenDecoration(
        CryptoRoutes.news,
        isWideScreen
    ),
    onRefresh: () -> Unit,
    toBuildIntent: (String, News) -> Unit,
) {
    LoadingContent(
        isLoading = newsState.value.isLoading,
        isEmpty = newsState.value.news.isEmpty(),
        emptyContent = { loadingContentDecorator.Screen },
        onRefresh = onRefresh
    ) {
        if (isWideScreen) {
            NewsWideScreen(news = newsState.value.news) { type, article ->
                toBuildIntent(
                    type,
                    article
                )
            }
        } else {
            NewsPortraitScreen(
                modifier,
                news = newsState.value.news
            ) { type, article -> toBuildIntent(type, article) }
        }
    }
}

@Composable
internal fun NewsWideScreen(
    modifier: Modifier = Modifier,
    news: List<News>,
    toBuildIntent: (String, News) -> Unit, // todo(
) {
    LazyColumn(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(news, key = { it.id }) { article ->
            SingleTitle(articleTitle = article.title)

            Row(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(end = 12.dp)
            ) {
                Column(modifier.fillMaxWidth(0.1f)) {
                    NewsSearchAndShareRail(toBuildIntent = { intentType ->
                        toBuildIntent(
                            intentType,
                            article
                        )
                    })
                }
                Column(modifier.fillMaxWidth(0.5f)) {
                    ArticleHeadLine(imageUrl = article.imageUrl)
                }

                Column(modifier.fillMaxWidth(0.9f)) {
                    SingleArticleBody(articleBody = article.body, articleTags = article.tags)
                }
            }
        }
    }
}

@Composable
internal fun NewsPortraitScreen(
    modifier: Modifier = Modifier,
    news: List<News>,
    toBuildIntent: (String, News) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(news, key = { it.id }) { article ->
            Row(modifier) {
                NewsSearchAndShareRail(toBuildIntent = { intentType ->
                    toBuildIntent(
                        intentType,
                        article
                    )
                })
                ArticleHeadLine(article.imageUrl, Modifier.weight(1f))
            }

            ArticleText(
                articleTitle = article.title,
                articleBody = article.body,
                articleTags = article.tags,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = modifier.padding(all = 20.dp))
        }
    }
}

@Composable
internal fun ArticleHeadLine(imageUrl: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(start = 8.dp)
                .padding(end = 8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl,
                    contentScale = ContentScale.Fit
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
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(all = 10.dp)
    )
}

@Composable
private fun NewsSearchAndShareRail(
    modifier: Modifier = Modifier,
    toBuildIntent: (String) -> Unit,

    ) {
    IconRail(
        modifier = modifier
            .padding(top = 50.dp, start = 8.dp)
            .width(40.dp)
            .height(250.dp),
        onClick = { toBuildIntent(it) },
        components = listOf(
            asBarFiller(share, SHARE), // todo intents
            asBarFiller(net, BROWSE)
        )
    )
}

@Composable
internal fun ArticleText(
    articleTitle: String,
    articleBody: String,
    articleTags: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(all = 0.dp)
            .fillMaxWidth()
    ) {
        articleTitle.HeadLineTextNews(modifier)
        Spacer(Modifier.padding(all = 10.dp))
        Divider(thickness = Dp.Hairline)
        articleBody.NewsText(modifier)
        Divider()
        articleTags.MiniText(modifier)
    }
}

@Composable
internal fun SingleTitle(modifier: Modifier = Modifier, articleTitle: String) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        articleTitle.HeadLineTextNews(modifier)
    }
}

@Composable
internal fun SingleArticleBody(
    modifier: Modifier = Modifier,
    articleBody: String,
    articleTags: String,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        articleBody.NewsText(modifier)
        Divider(modifier.padding(all = 8.dp))
        articleTags.MiniText(modifier)
    }
}
