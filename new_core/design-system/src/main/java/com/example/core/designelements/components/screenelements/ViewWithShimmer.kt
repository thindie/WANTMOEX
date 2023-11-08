package com.example.core.designelements.components.screenelements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designelements.components.screenelements.shimmering.rememberShimmerState
import com.example.core.designelements.theme.CryptoViewTheme
import kotlinx.coroutines.delay

@Composable
fun ViewWithShimmer(
    modifier: Modifier,
    shouldBeShimmered: Boolean,
    content: @Composable () -> Unit,
) {
    val color = LocalRippleTheme.current.defaultColor()
    val state = rememberShimmerState(shimmeringColor = color)
    val shimmeredColor = state.shimmeringBackground
    if (shouldBeShimmered) {
        Spacer(
            modifier = modifier
                .fillMaxSize()
                .background(shimmeredColor)
        )
    } else {
        content()
    }
}

@Composable
@Preview
fun previewShimmeredContent() {
    CryptoViewTheme {
        var shouldBeShimmered by remember { mutableStateOf(false) }
        val modifier = Modifier
        ViewWithShimmer(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            shouldBeShimmered = shouldBeShimmered,
        ) {
            Box(
                modifier.background(MaterialTheme.colorScheme.onPrimary)
            )
        }

        LaunchedEffect(key1 = Unit, block = {
            delay(2000)
            shouldBeShimmered = true
        })
    }
}
