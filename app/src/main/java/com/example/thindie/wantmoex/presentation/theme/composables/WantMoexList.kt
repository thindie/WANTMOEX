package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Share


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WantMoexList(list: List<Share>, onClick: (Share) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .imeNestedScroll()
        ) // scroll IME at the bottom
        {
            items(
                list
            ) { item: Share ->
                ShareItem(share = item) {
                    onClick(item)
                }
            }
        }
    }
}
