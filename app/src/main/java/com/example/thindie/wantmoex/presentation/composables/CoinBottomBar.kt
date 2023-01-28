package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoinBottomBar(
    onFavorites: () -> Unit,
    onNews: () -> Unit,
    onBack: () -> Unit,
    thisBarWithCoinList: Boolean
) {
    val paddingValues = PaddingValues(start = 4.dp, end = 12.dp, top = 12.dp)

    BottomAppBar(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
            if (thisBarWithCoinList) {
                IconButton(onClick = onNews) {
                    Icon(imageVector = Icons.Default.Newspaper, contentDescription = "news")
                }
                IconButton(onClick = onFavorites) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav")
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }



        }


    }
}
