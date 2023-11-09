package com.example.core.designelements.components.screenelements.shimmering

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun rememberShimmerState(
    transition: InfiniteTransition = rememberInfiniteTransition(label = ""),
    shimmeringColor: Color,
): ShimmerState {
    return remember {
        ShimmerState(
            transition,
            shimmerColor = shimmeringColor
        )
    }
}
