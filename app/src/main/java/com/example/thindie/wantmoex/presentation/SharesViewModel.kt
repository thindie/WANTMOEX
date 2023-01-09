package com.example.thindie.wantmoex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.wantmoex.data.StockDataRepositoryImpl
import com.example.thindie.wantmoex.di.DaggerWMComponent
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.domain.useCases.GetAllEntitiesUseCase
import com.example.thindie.wantmoex.domain.useCases.GetSingleEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SharesViewModel : ViewModel() {
    @Inject
    lateinit var impl: StockDataRepositoryImpl

    @Inject
    lateinit var getAllEntitiesUseCase: GetAllEntitiesUseCase

    @Inject
    lateinit var getSingleEntity: GetSingleEntity

    init {
        DaggerWMComponent.create().inject(this)
        loadAllData()
    }

    private val _uiState: MutableStateFlow<SharesUIState> = MutableStateFlow(
        SharesUIState.Loading(
            emptyList()
        )
    )

    val uiState: StateFlow<SharesUIState> = _uiState


     fun loadAllData() {
         viewModelScope.launch {
             delay(10)
             impl.getAll().collect { shareList ->
                 _uiState.value = SharesUIState.Success(shareList)
             }
         }

    }

    fun loadShare(share: Share) {
        viewModelScope.launch {
            impl.getSingle(share).collect { shareList ->
                _uiState.value = SharesUIState.Success(shareList)
            }
        }
    }


    sealed class SharesUIState {
        data class Success(val shares: List<Share>) : SharesUIState()
        data class Loading(val loads: List<Share>) : SharesUIState()
        data class Error(val exception: Throwable) : SharesUIState()
    }
}