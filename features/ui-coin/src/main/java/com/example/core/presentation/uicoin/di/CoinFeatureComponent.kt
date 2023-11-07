package com.example.core.presentation.uicoin.di

import com.example.cryptoview.application.di.DependenciesProvider
import com.example.cryptoview.application.viewmodelfactory.ViewModelFactory
import dagger.Component

@Component(dependencies = [DependenciesProvider::class], modules = [CoinsViewModelModule::class])
@CoinsScope
interface CoinFeatureComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): CoinFeatureComponent {
            return DaggerCoinFeatureComponent
                .factory()
                .create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(dependenciesProvider: DependenciesProvider): CoinFeatureComponent
    }

    fun viewModelsFactory(): ViewModelFactory
}
