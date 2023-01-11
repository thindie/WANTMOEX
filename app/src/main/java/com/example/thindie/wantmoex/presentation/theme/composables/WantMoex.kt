package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.presentation.SharesViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WantMoex(viewModel: SharesViewModel, onClick: (Share) -> Unit) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        val myScreenState = viewModel.uiState.collectAsState()

        when (myScreenState.value) {
            is SharesViewModel.SharesUIState.Loading -> CircularProgressIndicator()
            is SharesViewModel.SharesUIState.SuccessSingle -> {
                ShareItemExpanded(
                    list = (myScreenState.value as
                            SharesViewModel.SharesUIState.SuccessSingle).shares
                ) {
                    viewModel.loadAllData()
                }
            }
            is SharesViewModel.SharesUIState.SuccessAllShare -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .imeNestedScroll()
                ) // scroll IME at the bottom
                {
                    items(
                        (myScreenState.value
                                as SharesViewModel.SharesUIState.SuccessAllShare).shares
                    ) { item: Share ->
                        ShareItem(share = item) { onClick(item) }
                    }
                }
            }
            else -> {}
        }
    }
}