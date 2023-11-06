package com.example.core.data.coinlist.di

import com.example.core.network.apiservice.di.NetworkComponent
import com.example.core.network.apiservice.di.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoinsRepositoryModule::class], dependencies = [NetworkProvider::class])
interface CoinsComponent : CoinRepositoriesProvider {
    companion object {
        fun init(): CoinsComponent {
            val networkComponent = NetworkComponent.init()
            return DaggerCoinsComponent
                .factory()
                .create(networkComponent)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(provider: NetworkProvider): CoinsComponent
    }
}
