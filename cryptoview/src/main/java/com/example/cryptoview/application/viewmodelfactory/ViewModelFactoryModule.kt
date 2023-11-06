package com.example.cryptoview.application.viewmodelfactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory,
    ): ViewModelProvider.Factory
}
