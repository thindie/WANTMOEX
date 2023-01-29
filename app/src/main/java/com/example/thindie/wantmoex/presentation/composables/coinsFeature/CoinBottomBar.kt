package com.example.thindie.wantmoex.presentation.composables.coinsFeature

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val HEART_COLOR = false
private const val SHOW_STARS_ON_LIST_ELEMENTS = false
private const val GO_BACK = "back"
private const val GO_NEWS = "go_news"
private const val GO_FAVORITES = "go_favorites"
private const val SHOW_STAR = "gate_favor"


@Composable
fun CoinBottomBar(
    onClickedShowFavorites: () -> Unit,
    onClickedShowStars: () -> Unit,
    onBack: () -> Unit,
    onNews: () -> Unit,
) {


    val paddingValues = PaddingValues(start = 4.dp, end = 12.dp, top = 12.dp)
    var starColor by remember { mutableStateOf(SHOW_STARS_ON_LIST_ELEMENTS) }
    var heartColor by remember { mutableStateOf(HEART_COLOR) }

    var isFavoritesClicked by remember { mutableStateOf(HEART_COLOR) }
    var isShowStarsClicked by remember { mutableStateOf(SHOW_STARS_ON_LIST_ELEMENTS) }

    BottomAppBar(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                ///////
                onBack()
                starColor = SHOW_STARS_ON_LIST_ELEMENTS
                heartColor = HEART_COLOR
                //////
            }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = GO_BACK)
            }

            IconButton(onClick = onNews) {
                Icon(imageVector = Icons.Default.Newspaper, contentDescription = GO_NEWS)
            }
            IconButton(onClick = {
                ///
                heartColor = !heartColor
                onClickedShowFavorites()
                ////
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite, contentDescription = GO_FAVORITES,
                    tint = if (heartColor) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            Spacer(modifier = Modifier.weight(0.3f))
            IconButton(onClick = {
                /////
                starColor = !starColor
                onClickedShowStars()
                //////
            }) {
                Icon(
                    imageVector = Icons.Default.Star, contentDescription = SHOW_STAR,
                    tint = if (starColor) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

