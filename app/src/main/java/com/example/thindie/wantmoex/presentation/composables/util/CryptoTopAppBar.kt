package com.example.thindie.wantmoex.presentation.composables.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.presentation.composables.CryptoDestination
import com.example.thindie.wantmoex.presentation.composables.News

@Composable
fun CryptoTopAppbar(
    @StringRes resource: Int,
    tags: List<String>,
    destination: CryptoDestination,
    onClickTopAppbar: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .surfaceColor()
            .height(80.dp),
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        contentPadding = PaddingValues(start = 8.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
    ) {
        IconButton(onClick = { onClickTopAppbar() }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
        stringResource(id = resource).HeadLine()
        Spacer(modifier = Modifier.weight(0.8f))
        if (destination.route == News.route) {
            val string = if (tags.isEmpty()) "" else tags.joinToString(separator = " ,") { it }
            string.Mini()
        }
        Spacer(
            modifier = Modifier
                .eightEndPadding()
                .size(1.dp)
        )
    }
}
