package com.example.core.presentation.uinewsfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.consts.sharingStarted
import com.example.core.domain.domainnewsfeed.contract.NewsFetcher
import com.example.core.domain.domainnewsfeed.entity.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsFetcher: NewsFetcher) :
    ViewModel() {

    private val _newsMutableState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())

    var uiNewsState: StateFlow<NewsUiState> = _newsMutableState
        .stateIn(
            scope = viewModelScope,
            initialValue = NewsUiState(),
            started = sharingStarted
        )

    fun onLoadNews() {
        _newsMutableState.value = NewsUiState(isLoading = true)
        viewModelScope.launch {
            _newsMutableState.value = newsFetcher()
                .toNewsUiState()
        }
    }

    private fun List<News>.toNewsUiState(): NewsUiState {
        return NewsUiState(news = this)
    }

    data class NewsUiState(
        val news: List<News> = emptyList(),
        val isLoading: Boolean = false,
    )
}
