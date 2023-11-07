package com.example.core.presentation.uicoin.di

import dagger.Component

@Component(modules = [CoinsViewModelModule::class])
@CoinsScope
interface CoinFeatureComponent : CoinFeatureProvider {
    companion object {
        fun init(): CoinFeatureComponent {
            return DaggerCoinFeatureComponent
                .factory()
                .create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): CoinFeatureComponent
    }
}
