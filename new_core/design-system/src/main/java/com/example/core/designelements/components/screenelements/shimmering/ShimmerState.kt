package com.example.core.designelements.components.screenelements.shimmering

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Stable
class ShimmerState(
    private val transition: InfiniteTransition,
    private val shimmerColor: Color,
) {

    private val duration = Random.nextInt(from = 700, until = 1300)

    private val shimmersColorsList
        @Composable get() = buildList {
            val tries = 7
            repeat(tries) {
                this.add(shimmerColor.copy(1 - (it * 0.1.toFloat())))
            }
            repeat(tries) {
                this.add(shimmerColor.copy(1 - ((tries - it) * 0.1.toFloat())))
            }
        }

    private val translateAnimation
        @Composable get() = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(duration, easing = LinearOutSlowInEasing, delayMillis = 400),
                repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )

    val shimmeringBackground
        @Composable get() = Brush.linearGradient(
            colors = shimmersColorsList,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
}
