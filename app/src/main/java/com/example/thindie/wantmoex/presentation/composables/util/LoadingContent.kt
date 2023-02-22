package com.example.thindie.wantmoex.presentation.composables.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
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
    val color = MaterialTheme.colorScheme.surfaceVariant
    val shimmers = listOf(
        color.copy(0.6f), color.copy(0.2f), color.copy(0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutLinearInEasing), repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmers,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    LazyColumn(modifier = Modifier.surfaceColor()) {
        items(9) {
            ShimmerElement(brush = brush)
        }
    }

}

@Composable
fun ShimmerElement(brush: Brush) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .surfaceColor()
            .height(80.dp)
    ) {

        Column(
            Modifier.weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(brush)
            )
        }
        Column(
            Modifier.weight(0.8f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(Modifier.padding(all = 10.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(15.dp)
                        .clip(Shapes.extraLarge)
                        .background(brush)
                )
            }
            Row(Modifier.padding(all = 10.dp)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(10.dp)
                        .clip(Shapes.extraLarge)
                        .background(brush)
                )
                Spacer(
                    modifier = Modifier
                        .height(Dp.Hairline)
                        .fillMaxWidth()
                        .background(brush)
                )
            }
        }


    }
}

