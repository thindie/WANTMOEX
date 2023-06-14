package com.example.core.designelements.components.screenelements.monosystemscreens

import androidx.compose.runtime.Composable
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


