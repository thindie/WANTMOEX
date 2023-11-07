package com.example.core.data.coinlist.di

import com.example.core.network.apiservice.di.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoinsRepositoryModule::class], dependencies = [NetworkProvider::class])
interface CoinsDataComponent : CoinRepositoriesProvider {
    companion object {
        fun init(networkProvider: NetworkProvider): CoinsDataComponent {
            return DaggerCoinsDataComponent
                .factory()
                .create(networkProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(provider: NetworkProvider): CoinsDataComponent
    }
}
