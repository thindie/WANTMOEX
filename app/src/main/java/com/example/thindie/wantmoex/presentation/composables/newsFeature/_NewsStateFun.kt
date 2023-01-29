package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.NewsViewModel
import com.example.thindie.wantmoex.presentation.composables.util.ErrorScreen
import com.example.thindie.wantmoex.presentation.composables.util.LoadScreen
import com.example.thindie.wantmoex.route.beginTransition

private const val WAIT_TIME = 100L

@Composable
fun NewsStateFun(newsViewModel: NewsViewModel = viewModel()) {
    val context = LocalContext.current
    val newsViewState by newsViewModel.viewState.collectAsStateWithLifecycle()

    when (newsViewState) {
        is NewsViewModel.ViewState.Loading -> {
            LoadScreen(waitTime = WAIT_TIME) { newsViewModel.onLoadNews() }
        }
        is NewsViewModel.ViewState.Error -> {
            ErrorScreen { beginTransition<NewsActivity, MainActivity>(context) }
        }
        is NewsViewModel.ViewState.SuccessNews -> {
            val newsList = (newsViewState as NewsViewModel.ViewState.SuccessNews).newsList
            newsList.shuffled()
            NewsScreen(newsList = newsList)
        }
    }
}