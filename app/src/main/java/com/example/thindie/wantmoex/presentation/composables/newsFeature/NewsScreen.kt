package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.CoinBottomBar
import com.example.thindie.wantmoex.presentation.composables.CoinTopAppBar
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition

private const val MORE_THAN_ONE = 1
private const val TITLE = "News "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    list: List<News>,
) {

    val context = LocalContext.current

    val title = remember {
        mutableStateOf(TITLE)
    }
    val scaffoldShowList by remember {
        mutableStateOf(list.size > MORE_THAN_ONE)
    }


    LaunchedEffect(key1 = scaffoldShowList) {
        animateTextByDotsOnStateBased(title.value, title)
    }


    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        bottomBar = {
            CoinBottomBar(
                onFavorites = {/*ITS DONT NEED THERE*/ },
                onNews = {/*ITS DONT NEED THERE*/ },
                onBack = { beginTransition<NewsActivity, MainActivity>(context) },
                thisBarWithoutCoinList = false
            )
        },
        topBar = {
            CoinTopAppBar(title = title.value, onClick = { })

        }

    ) { it ->
        if (scaffoldShowList) {
            title.value = TITLE
            LazyColumn(modifier = Modifier.padding(it)) {
                items(list) {
                    NewsElement(news = it)
                }
            }


        }
    }
}
