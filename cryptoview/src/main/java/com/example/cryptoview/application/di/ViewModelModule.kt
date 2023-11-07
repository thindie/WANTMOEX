package com.example.cryptoview.application.di

import androidx.lifecycle.ViewModel
import com.example.core.presentation.uicoin.viewmodel.CoinsViewModel
import com.example.cryptoview.activity.MainViewModel
import com.example.cryptoview.application.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinsViewModel::class)
    fun bindCoinsViewModel(coinsViewModel: CoinsViewModel): ViewModel
}
