package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.Result
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.domain.useCases.GetAllActualNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getAllActualNewsUseCase: GetAllActualNewsUseCase) :
    ViewModel() {


    private var _uiNewsState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val uiNewsState: StateFlow<NewsUiState>
        get() = _uiNewsState.asStateFlow()


    fun onLoadNews() {
        viewModelScope.launch {
            getAllActualNewsUseCase()
                .map { produceNewsUiState(it) }
                .collect {
                    _uiNewsState.value = it
                }
        }
    }

    private fun produceNewsUiState(result: Result<List<News>?>): NewsUiState {
        return when (result) {
            is Result.Success -> {
                NewsUiState(news = result.data.toList(), isLoading = false)
            }
            is Result.Error -> {
                NewsUiState()
            }
        }
    }
}

data class NewsUiState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = true,
    val filterNews: String? = null,
)