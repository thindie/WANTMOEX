package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition


private const val TITLE = "News "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    newsList: List<News>,
) {

    val context = LocalContext.current
    val title = remember {
        mutableStateOf(TITLE)
    }

    LaunchedEffect(key1 = true) {
        animateTextByDotsOnStateBased(title.value, title)
    }


    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        bottomBar = {
            NewsBottomBar {

                beginTransition<NewsActivity, MainActivity>(context)

            }
        },
        topBar = { NewsTopAppBar(title = TITLE) { } })
    { freshNew ->
        title.value = TITLE
        LazyColumn(modifier = Modifier.padding(freshNew)) {
            items(newsList) {
                NewsElement(news = it)
            }
        }
    }
}

