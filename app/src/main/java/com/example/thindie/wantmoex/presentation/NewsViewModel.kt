package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.domain.useCases.GetAllActualNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getAllActualNewsUseCase: GetAllActualNewsUseCase) :
    ViewModel() {

    private val _viewState: MutableStateFlow<NewsViewModel.ViewState> =
        MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState>
        get() = _viewState

    fun onLoadNews() {
        viewModelScope.launch {
            getAllActualNewsUseCase.invoke().collect {
                TODO()
            }
        }
    }

    sealed class ViewState {
        data class SuccessNews(val newsList: List<News>) : ViewState()
        object Error : ViewState()
        object Loading : ViewState()
    }
}