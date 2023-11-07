package com.example.cryptoview.application.di

import android.content.Context
import com.example.core.data.coinlist.di.CoinRepositoriesProvider
import com.example.core.data.coinlist.di.CoinsDataComponent
import com.example.core.data.uistatesettings.di.SettingsComponent
import com.example.core.data.uistatesettings.di.SettingsProvider
import com.example.core.network.apiservice.di.NetworkComponent
import com.example.core.network.apiservice.di.NetworkProvider
import com.example.core.presentation.uicoin.di.CoinFeatureComponent
import com.example.core.presentation.uicoin.di.CoinFeatureProvider
import com.example.cryptoview.activity.ActivityMain
import com.example.cryptoview.application.viewmodelfactory.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        CoinRepositoriesProvider::class,
        SettingsProvider::class,
        NetworkProvider::class,
        CoinFeatureProvider::class
    ],
    modules = [ViewModelFactoryModule::class, ViewModelModule::class]
)
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val networkProvider = NetworkComponent.init()
            val coinRepositoryProvider = CoinsDataComponent.init(networkProvider)
            val settingsProvider = SettingsComponent.init()
            val coinsFeatures = CoinFeatureComponent.init()
            return DaggerAppComponent
                .factory()
                .create(
                    context = context,
                    networkProvider = networkProvider,
                    coinsRepositoriesProvider = coinRepositoryProvider,
                    settingsProvider = settingsProvider,
                    coinFeatureProvider = coinsFeatures
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            networkProvider: NetworkProvider,
            coinsRepositoriesProvider: CoinRepositoriesProvider,
            settingsProvider: SettingsProvider,
            coinFeatureProvider: CoinFeatureProvider,
        ): AppComponent
    }

    fun inject(activity: ActivityMain)
}
