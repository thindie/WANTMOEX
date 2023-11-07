package com.example.core.presentation.uicoin.di

import androidx.lifecycle.ViewModel
import com.example.core.presentation.uicoin.viewmodel.CoinsViewModel
import com.example.cryptoview.application.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface CoinsViewModelModule {
    @Binds
    @CoinsScope
    @IntoMap
    @ViewModelKey(CoinsViewModel::class)
    fun bindCoinsViewModel(coinsViewModel: CoinsViewModel): ViewModel
}
