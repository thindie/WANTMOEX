package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.theme.composables.details.CoinList

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CoinHomeScreen(list: List<Coin>, onClickElement: (String) -> Unit) {

    Scaffold(bottomBar = {}) {
        CoinList(modifier = Modifier.padding(it), onClickElement, list)
    }
}

