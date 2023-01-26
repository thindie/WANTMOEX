package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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

fun CoinErrorScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Surface(modifier = modifier.fillMaxSize()) {


        val currentTimeOut by rememberUpdatedState(newValue = onTimeout)

        LaunchedEffect(true) {

            delay(WAIT_TIME)
            currentTimeOut()
        }

        Image(painterResource(id = R.drawable.no_data), contentDescription = null)
        CircularProgressIndicator(
            strokeWidth = 70.dp,
            modifier = Modifier
                .scale(0.1f)

        )
    }
}
