package com.example.core.presentation.uicoin.coinscreen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.uicoin.viewmodel.CoinsViewModel

@Composable
fun CoinsScreen(
    factory: ViewModelProvider.Factory,
    viewModel: CoinsViewModel = viewModel(factory = factory),
) {
    Column(Modifier.fillMaxSize()) {
        Text("hello fromCoinsScreen")
    }
    val state by viewModel.observe.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Column(
        Modifier.scrollable(
            rememberScrollableState(consumeScrollDelta = { 1f }),
            orientation = Orientation.Vertical
        )
    ) {
        state.forEach {
            Text(text = it.fromSymbol)
        }
    }
}
