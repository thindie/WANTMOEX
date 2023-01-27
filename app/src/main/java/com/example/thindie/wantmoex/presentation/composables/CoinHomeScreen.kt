package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.composables.coinsFeature.CoinDetailScreen
import com.example.thindie.wantmoex.presentation.composables.coinsFeature.CoinList
import com.example.thindie.wantmoex.presentation.composables.newsFeature.NewsHomeScreen
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased

private const val MORE_THAN_ONE = 1
private const val THE_ONE = 0
private const val TITLE = "Coins "
private const val INITIAL_STRING = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinHomeScreen(
    list: List<Coin>,
    onClickElement: (String) -> Unit,
    onClickBack: () -> Unit,
) {


    val title = remember {
        mutableStateOf(TITLE)
    }
    val scaffoldShowList by remember {
        mutableStateOf(list.size > MORE_THAN_ONE)
    }

    var showNews by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = scaffoldShowList) {
        animateTextByDotsOnStateBased(title.value, title)
    }

    if (!showNews) {
        Scaffold(
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            bottomBar = {},
            topBar = {
                CoinTopAppBar(
                    title = title.value,
                    onClick = {
                        showNews = !showNews
                    },
                )
            }

        ) {
            if (scaffoldShowList) {
                title.value = TITLE
                CoinList(modifier = Modifier.padding(it), onClickElement, list)
            } else {
                CoinDetailScreen(
                    list[THE_ONE],
                    { onClickBack() },
                    modifier = Modifier.padding(it)
                );title.value = INITIAL_STRING
            }
        }
    } else NewsHomeScreen()


}

