package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.NewsViewModel
import com.example.thindie.wantmoex.presentation.composables.util.ErrorScreen
import com.example.thindie.wantmoex.presentation.composables.util.LoadScreen

private const val WAIT_TIME = 100L

@Composable
fun NewsStateFun(newsViewModel: NewsViewModel = viewModel()) {

    val newsViewState by newsViewModel.viewState.collectAsStateWithLifecycle()

    when (newsViewState) {
        is NewsViewModel.ViewState.Loading -> {
            LoadScreen(waitTime = WAIT_TIME) { newsViewModel.onLoadNews() }
        }
        is NewsViewModel.ViewState.Error -> {
            ErrorScreen { }
        }
        is NewsViewModel.ViewState.SuccessNews -> {
            val newsList = (newsViewState as NewsViewModel.ViewState.SuccessNews).newsList
            newsList.shuffled()
            NewsScreen(newsList = newsList)
        }
    }
}