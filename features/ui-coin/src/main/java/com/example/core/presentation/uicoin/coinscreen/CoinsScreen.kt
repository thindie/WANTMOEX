package com.example.core.presentation.uicoin.coinscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.uicoin.viewmodel.CoinsViewModel
import com.example.cryptoview.application.viewmodelfactory.ViewModelFactory

@Composable
internal fun CoinsScreen(
    factory: ViewModelFactory,
    viewModel: CoinsViewModel = viewModel(factory = factory),
) {
    Text("hello fromCoinsScreen")
}
