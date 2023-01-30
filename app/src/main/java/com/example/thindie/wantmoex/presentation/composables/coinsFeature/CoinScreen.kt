package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Moving
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.presentation.CoinUIModel
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition
import kotlinx.coroutines.launch

private const val TITLE = "Coins "


@Composable
fun CoinScreen(
    list: List<CoinUIModel>,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    onClickFavorites: () -> Unit,
    onClickElement: (String) -> Unit,
    onClickBack: () -> Unit,
) {

    val context = LocalContext.current
    val title = remember { mutableStateOf(TITLE) }
    var revealedFavoritesSection by remember { mutableStateOf(false) }

    val scaffoldState = rememberScaffoldState()
    val scaffoldScope = rememberCoroutineScope()

    val drawerMenuItems = listOf(
        DrawerMenuItem(
            Icons.Default.Home,
            "Home",
            MaterialTheme.typography.bodyLarge
        ) { },
        DrawerMenuItem(
            Icons.Default.Settings,
            "Set",
            MaterialTheme.typography.bodyLarge
        ) { },
        DrawerMenuItem(
            Icons.Default.Moving,
            "Others",
            MaterialTheme.typography.bodyLarge
        ) { },
    )


    LaunchedEffect(key1 = true) {
        animateTextByDotsOnStateBased(title.value, title)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            CoinDrawerHeader(); NewsDrawerBody(drawerMenuItems)
        },
        drawerBackgroundColor = MaterialTheme.colorScheme.surface,
        backgroundColor = MaterialTheme.colorScheme.surface,
        drawerElevation = 80.dp,
        bottomBar = {
            CoinBottomBar(
                onClickedShowFavorites = onClickFavorites,
                onBack = onClickBack,
                onClickedShowStars = {
                    revealedFavoritesSection = revealedFavoritesSection.not()
                }) {
                beginTransition<NewsActivity, MainActivity>(context)
            }
        },
        topBar = { CoinTopAppBar(title = title.value) { scaffoldScope.launch { scaffoldState.drawerState.open() } } },

        ) {
        CoinList(
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
private fun CoinList(
    modifier: Modifier,
    revealedFavoritesSection: Boolean,
    onClickElement: (String) -> Unit,
    onFavoritesAdded: (String) -> Unit,
    onFavoritesDeleted: (String) -> Unit,
    list: List<CoinUIModel>,
) {
    LazyColumn(
        modifier = modifier,
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
