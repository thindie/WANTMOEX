package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.theme.composables.details.CoinList
import com.example.thindie.wantmoex.presentation.theme.composables.details.CoinTopAppBar
import com.example.thindie.wantmoex.presentation.theme.composables.details.playWithTitle
import com.example.thindie.wantmoex.presentation.theme.composables.details.whenUpdated

private const val MORE_THAN_ONE = 1
private const val THE_FRESHEST = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinHomeScreen(list: List<Coin>, onClickElement: (String) -> Unit) {


    val title = remember {
        mutableStateOf("Coins ")
    }

    LaunchedEffect(key1 = true) {
        playWithTitle(title.value, title)
    }

    Scaffold(
        bottomBar = {},
        topBar = {
            CoinTopAppBar(
                title = title.value,
                onClick = {},
                whenUpdated(list[THE_FRESHEST].lastUpdate)
            )
        }

    ) {
        if (list.size > MORE_THAN_ONE) {
            CoinList(modifier = Modifier.padding(it), onClickElement, list)
        } else CoinDetailScreen()
    }
}

