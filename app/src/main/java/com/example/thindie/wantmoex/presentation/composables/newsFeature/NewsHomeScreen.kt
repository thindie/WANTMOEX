package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.wantmoex.presentation.NewsViewModel

@Composable
fun NewsHomeScreen(newsViewModel: NewsViewModel = viewModel()){
        val newsViewState = newsViewModel.viewState.collectAsState()
}