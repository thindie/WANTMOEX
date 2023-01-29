package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition

private const val TITLE = "Favorites "


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinFavoriteCoinsScreen(
    list: List<CoinUIModel>,
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
                onClickedShowFavorites = onClickFavourites,
                onBack = onClickBack,
                onClickedShowStars = {
                    revealedFavoritesSection = revealedFavoritesSection.not()
                }) {
                beginTransition<NewsActivity, MainActivity>(context)
            }
        },
        topBar = { CoinTopAppBar(title = title.value, onClick = { }) }

    ) {
        title.value = TITLE

        CoinFavoriteList(
            modifier = Modifier.padding(it),
            revealedFavoritesSection,
            onClickElement,
            onFavoritesAdded,
            onFavoritesDeleted,
            list,
        )
    }
}


@Composable
private fun CoinFavoriteList(
    modifier: Modifier,
    revealedFavoritesSection: Boolean,
    onClickElement: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    list: List<CoinUIModel>,
) {
    val state = rememberLazyListState()


    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        items(list) { coinItem ->
            CoinListElement(
                coin = coinItem,
                showFavoriteSymbol = revealedFavoritesSection,
                onFavoritesAdded,
                onFavoritesDeleted,
                onClickElement
            )
        }
    }
}

