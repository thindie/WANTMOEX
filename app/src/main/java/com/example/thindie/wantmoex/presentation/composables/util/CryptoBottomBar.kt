package com.example.thindie.wantmoex.presentation.composables.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.presentation.composables.*

@Composable
fun CryptoCoinsBottomBar(
    onSelectedDestination: (String) -> Unit,
    onExpandCoins: () -> Unit,
) {

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        with(onSelectedDestination) {
            CryptoMoveTo(destination = Coins, onClick = this)
            CryptoMoveTo(destination = FavoriteCoins, onClick = this)
            CryptoMoveTo(destination = News, onClick = this)
            Spacer(modifier = Modifier.weight(1f))
            CryptoMoveTo(destination = CoinsExpandedView, onClick = { onExpandCoins() })
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
