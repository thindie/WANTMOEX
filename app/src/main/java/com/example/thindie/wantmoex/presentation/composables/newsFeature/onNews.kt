package com.example.thindie.wantmoex.presentation.composables.newsFeature

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.NewsViewModel
import com.example.thindie.wantmoex.presentation.composables.CoinErrorScreen
import com.example.thindie.wantmoex.presentation.composables.CoinLoadScreen

private const val WAIT_TIME = 300L
@Composable
fun NewsHomeScreen(newsViewModel: NewsViewModel = viewModel()) {
    val newsViewState by newsViewModel.viewState.collectAsStateWithLifecycle()

    when (newsViewState) {
        is NewsViewModel.ViewState.Loading -> {
            CoinLoadScreen(waitTime = WAIT_TIME) {
                newsViewModel.onLoadNews()
            }
        }
        is NewsViewModel.ViewState.Error -> {
            CoinErrorScreen {

            }
        }
        is NewsViewModel.ViewState.SuccessNews -> {
            val newsList = (newsViewState as NewsViewModel.ViewState.SuccessNews).newsList
            newsList.shuffled()
            NewsList(list = newsList, onClickElement = {}) {

            }
        }
    }
}