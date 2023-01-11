package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.domain.useCases.GetAllEntitiesUseCase
import com.example.thindie.wantmoex.domain.useCases.GetSingleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharesViewModel @Inject constructor(
    private val getAllEntitiesUseCase: GetAllEntitiesUseCase,
    private val getSingleEntity: GetSingleEntity,
) : ViewModel() {


    private val _uiState: MutableStateFlow<SharesUIState> = MutableStateFlow(
        SharesUIState.Loading(
            emptyList()
        )
    )

    val uiState: StateFlow<SharesUIState> = _uiState

    init {
        loadAllData()
    }

    fun loadAllData() {
        viewModelScope.launch {
            _uiState.value = SharesUIState.Loading(emptyList())
            delay(100)
            getAllEntitiesUseCase.invoke().collect { shareList ->
                _uiState.value = SharesUIState.SuccessAllShare(shareList)
            }
        }

    }


    fun loadShare(share: Share) {
        viewModelScope.launch {
            _uiState.value = SharesUIState.Loading(emptyList())
            delay(150)
            getSingleEntity.invoke(share).collect { shareList ->
                _uiState.value = SharesUIState.SuccessSingle(shareList)
            }
        }
    }


    sealed class SharesUIState {
        data class SuccessAllShare(val shares: List<Share>) : SharesUIState()
        data class SuccessSingle(val shares: List<Share>) : SharesUIState()
        data class Loading(val loads: List<Share>) : SharesUIState()
        data class Error(val exception: Throwable) : SharesUIState() //TODO заглушка
    }
}