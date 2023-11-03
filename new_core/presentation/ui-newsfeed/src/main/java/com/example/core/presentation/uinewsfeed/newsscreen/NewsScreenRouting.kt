package com.example.core.presentation.uinewsfeed.newsscreen

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.createChooser
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.common.consts.BROWSE
import com.example.core.common.consts.SHARE
import com.example.core.designelements.cryptoroutes.CryptoRoutes
import com.example.core.domain.domainnewsfeed.entity.News
import com.example.core.presentation.uinewsfeed.viewmodel.NewsViewModel

fun NavGraphBuilder.newsScreen(isWideScreen: Boolean, executeIntent: (Intent) -> Unit) {
    composable(route = CryptoRoutes.news) {
        NewsScreenState(isWideScreen = isWideScreen, executeIntent = executeIntent)
    }
}

@Composable
internal fun NewsScreenState(
    viewModel: NewsViewModel = hiltViewModel(),
    isWideScreen: Boolean,
    executeIntent: (Intent) -> Unit,
    fetch: () -> Unit = viewModel::onLoadNews,
) {
    fetch()
    val state =
        viewModel
            .uiNewsState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    NewsScreen(
        modifier = Modifier.fillMaxWidth(),
        state,
        onRefresh = fetch,
        isWideScreen = isWideScreen
    ) { type, article -> executeIntent(buildIntent(article, type)) }
}

private fun buildIntent(article: News, route: String): Intent {
    if (route == SHARE) {
        return shareIntent(article)
    }
    if (route == BROWSE) {
        return openWebPage(article.url)
    }
    throw IllegalArgumentException(
        "where: NewsScreenRouting, in build Intent" +
                "why: Unexpected route to build Intent chained to NewsScreen actions" +
                "result: crush"
    )
}

private fun openWebPage(url: String?): Intent {
    val webpage: Uri = Uri.parse(url)
    return Intent(Intent.ACTION_VIEW, webpage)
}

private fun shareIntent(article: News): Intent {
    val shareIntent: Intent = Intent().apply {
        action = ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, article.url)
        type = "text/plain"
    }
    return createChooser(shareIntent, null)
}
