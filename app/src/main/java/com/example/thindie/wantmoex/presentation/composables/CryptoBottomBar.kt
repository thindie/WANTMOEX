package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CryptoCoinsBottomBar(
    onSelectedDestination: (String) -> Unit,
) {


    val paddingValues = PaddingValues(start = 4.dp, end = 12.dp, top = 12.dp)
    var starColor by remember { mutableStateOf(SHOW_STARS_ON_LIST_ELEMENTS) }
    var heartColor by remember { mutableStateOf(HEART_COLOR) }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            with(onSelectedDestination) {
                CryptoMoveTo(destination = Coins, onClick = this)
                CryptoMoveTo(destination = FavoriteCoins, onClick = this)
                CryptoMoveTo(destination = News, onClick = this)
                Spacer(modifier = Modifier.weight(1f))
                CryptoMoveTo(destination = CoinsExpandedView, onClick = this)
            }
        }
    }
}

@Composable
fun CryptoMoveTo(destination: CryptoDestination, onClick: (String) -> Unit) {
    IconButton(onClick = { onClick(destination.route) }
    ) {
        Icon(imageVector = destination.icon, contentDescription = destination.route)
    }
}


private const val HEART_COLOR = false
private const val SHOW_STARS_ON_LIST_ELEMENTS = false
