package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Share

@Composable
fun WantMoex(state: State<List<Share>>, modifier: Modifier) {
    Surface(modifier = modifier) {
        ShareColumn()
    }
}