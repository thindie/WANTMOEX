package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.composables.CoinTopAppBar
import com.example.thindie.wantmoex.presentation.composables.StartScreen
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased

private const val MORE_THAN_ONE = 1
private const val TITLE = "News "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(list: List<News>, onClickElement: (String) -> Unit, onClickBack: () -> Unit) {

    val title = remember {
        mutableStateOf(TITLE)
    }
    val scaffoldShowList by remember {
        mutableStateOf(list.size > MORE_THAN_ONE)
    }

    var showNews by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = scaffoldShowList) {
        animateTextByDotsOnStateBased(title.value, title)
    }

    if(showNews){
        Scaffold(
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            bottomBar = {},
            topBar = {
                CoinTopAppBar(
                    title = title.value,
                    onClick = {showNews = !showNews},

                    )
            }

        ) { it ->
            if (scaffoldShowList) {
                title.value = TITLE
                LazyColumn(modifier = Modifier.padding(it)) {
                    items(list) {
                        NewsElement(news = it, {})
                    }
                }

            } else {
                /*CoinDetailScreen(
                    list[THE_ONE],
                    { onClickBack() },
                    modifier = Modifier.padding(it)
                );title.value = INITIAL_STRING*/
            }
        }
    }
    else StartScreen()

}

