package com.example.core.network.di

import dagger.Component

@Component(modules = [NetworkServiceModule::class, NetworkUtilsModule::class])
interface NetworkComponent : NetworkProvider {
    companion object {
        fun init(): NetworkComponent {
            return DaggerNetworkComponent
                .factory()
                .create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }
}
