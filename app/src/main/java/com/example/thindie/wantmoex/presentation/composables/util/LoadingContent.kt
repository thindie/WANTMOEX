package com.example.thindie.wantmoex.presentation.composables.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.presentation.theme.Shapes
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun LoadingContent(
    isLoading: Boolean,
    isEmpty: Boolean,
    emptyContent: @Composable () -> Unit,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (isEmpty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isLoading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}

@Composable
fun ColorShimmer() {
    val shimmers = listOf(
        Color.Gray.copy(0.6f),
        Color.Gray.copy(0.2f),
        Color.Gray.copy(0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmers,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    ShimmerElement(brush = brush)
}

@Composable
fun ShimmerElement(brush: Brush) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .height(80.dp)
    )
    {
        Column(Modifier.weight(0.2f)) {
            Spacer(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(brush)
            )
        }
        Column(Modifier.weight(0.8f)) {
            Row(Modifier.padding(all = 20.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(20.dp)
                        .clip(Shapes.extraLarge)
                        .background(brush)
                )
            }
            Row(Modifier.padding(all = 20.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .clip(Shapes.extraLarge)
                        .background(brush)
                )
            }
        }
    }
}