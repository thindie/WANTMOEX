package com.example.cryptoview.application.di

import android.content.Context
import com.example.core.data.coinlist.di.CoinRepositoriesProvider
import com.example.core.data.coinlist.di.CoinsComponent
import com.example.core.data.uistatesettings.di.SettingsComponent
import com.example.core.data.uistatesettings.di.SettingsProvider
import com.example.cryptoview.activity.ActivityMain
import com.example.cryptoview.application.viewmodelfactory.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoinRepositoriesProvider::class, SettingsProvider::class],
    modules = [ViewModelFactoryModule::class, MainViewModelModule::class]
)
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val coinRepositoryProvider = CoinsComponent.init()
            val settingsProvider = SettingsComponent.init()
            return DaggerAppComponent
                .factory()
                .create(context, coinRepositoryProvider, settingsProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            coinsRepositoriesProvider: CoinRepositoriesProvider,
            settingsProvider: SettingsProvider,
        ): AppComponent
    }

    fun inject(activity: ActivityMain)
}
