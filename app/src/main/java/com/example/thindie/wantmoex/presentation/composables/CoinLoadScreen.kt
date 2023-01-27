package com.example.thindie.wantmoex.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.R
import kotlinx.coroutines.delay



@Composable

fun CoinLoadScreen(modifier: Modifier = Modifier, waitTime: Long,onTimeout: () -> Unit ) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(modifier = modifier.clip(CircleShape), contentAlignment = Alignment.Center) {

            val currentTimeOut by rememberUpdatedState(newValue = onTimeout)

            LaunchedEffect(true) {

                delay(waitTime)
                currentTimeOut()
            }

            Image(painterResource(id = R.drawable.crypto), contentDescription = null)
            LinearProgressIndicator(
                modifier = Modifier
                    .scale(0.3f)
                    .padding(top = 350.dp)

            )
        }
    }

}