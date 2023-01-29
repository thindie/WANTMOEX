package com.example.thindie.wantmoex.presentation.composables.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.R
import kotlinx.coroutines.delay

private const val WAIT_TIME: Long = 3000

@Composable

fun ErrorScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    val currentTimeOut by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(true) {
        delay(WAIT_TIME)
        currentTimeOut()
    }


    Surface(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            Image(
                painterResource(id = R.drawable.onerror),
                contentDescription = null,
                modifier = Modifier.scale(0.5f)
            )
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()

            )
        }
    }

}
