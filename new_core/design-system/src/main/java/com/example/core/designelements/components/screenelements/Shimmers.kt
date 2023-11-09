package com.example.core.designelements.components.screenelements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designelements.components.screenelements.shimmering.rememberShimmerState
import com.example.core.designelements.theme.CryptoViewTheme
import kotlinx.coroutines.delay

@Composable
fun Shimmers(
    shouldBeShimmered: Boolean,
    content: @Composable (Modifier) -> Unit,
) {
    if (shouldBeShimmered) {
        val color = LocalRippleTheme.current.defaultColor()
        val state = rememberShimmerState(shimmeringColor = color)
        val shimmeredColor = state.shimmeringBackground
        content(Modifier.background(shimmeredColor))
    } else {
        content(Modifier)
    }
}

@Composable
@Preview
fun previewShimmeredContent() {
    CryptoViewTheme {
        var shouldBeShimmered by remember { mutableStateOf(true) }
        val modifier = Modifier
        Shimmers(
            shouldBeShimmered = shouldBeShimmered,
        ) {
            Spacer(
                modifier = it
                    .size(200.dp)
                    .background(Color.Red)
                    .then(it)
            )
        }

        LaunchedEffect(key1 = Unit, block = {
            delay(2000)
            shouldBeShimmered = false
        })
    }
}
