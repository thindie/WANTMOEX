package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.domain.unpackResult
import com.example.thindie.wantmoex.domain.useCases.GetAllActualNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getAllActualNewsUseCase: GetAllActualNewsUseCase) :
    ViewModel() {

    private val _news: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var uiNewsState: StateFlow<NewsUiState> = combine(_news, _isLoading) { news, isLoading ->
        val newsUiState = if (!isLoading) {
            if (news.isEmpty()) {
                NewsUiState(isLoading = isLoading)
            } else NewsUiState(news = news, isLoading = isLoading)
        } else {
            _isLoading.value = false; _news.value = emptyList()
            NewsUiState()
        }
        newsUiState
    }.stateIn(
        scope = viewModelScope,
        initialValue = NewsUiState(),
        started = SharingStarted.WhileSubscribed(TIMEOUT)
    )


    fun onLoadNews(list: List<String>) {
        viewModelScope.launch {
            val newsFeed = getAllActualNewsUseCase.getAllNews(list).unpackResult {
                it
            } ?: emptyList()
            _isLoading.value = false
            _news.value = newsFeed
        }
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}

data class NewsUiState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = true,
)