package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition

private const val TITLE = "Coins "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinScreen(
    list: List<Coin>,
    favoriteList: List<String>,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onClickFavourites: () -> Unit,
    onClickElement: (String) -> Unit,
    onClickBack: () -> Unit,
) {
    val context = LocalContext.current

    val title = remember { mutableStateOf(TITLE) }
    var revealedFavoritesSection by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        animateTextByDotsOnStateBased(title.value, title)
    }

    Scaffold(
        bottomBar = {
            CoinBottomBar(
                onFavorites = { onClickFavourites() },
                onBack = { onClickBack() },
                onClickedShowFavorites = {
                    revealedFavoritesSection = !revealedFavoritesSection
                }) {
                beginTransition<NewsActivity, MainActivity>(context)
            }
        },
        topBar = { CoinTopAppBar(title = title.value, onClick = { }) }

    ) {
        title.value = TITLE
        if (revealedFavoritesSection) {
            CoinList(
                modifier = Modifier.padding(it),
                onClickElement,
                onFavoritesAdded,
                onFavoritesDeleted,
                list,
                favoriteList
            )
        } else {
            CoinList(
                modifier = Modifier.padding(it),
                onClickElement,
                onFavoritesAdded,
                onFavoritesDeleted,
                list,
                null
            )
        }
    }
}

