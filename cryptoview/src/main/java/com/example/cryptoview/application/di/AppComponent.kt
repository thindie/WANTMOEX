package com.example.cryptoview.application.di

import android.content.Context
import com.example.core.data.coinlist.di.CoinRepositoriesProvider
import com.example.core.data.coinlist.di.CoinsComponent
import com.example.cryptoview.activity.ActivityMain
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [CoinRepositoriesProvider::class])
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val coinRepositoryProvider = CoinsComponent.init()
            return DaggerAppComponent
                .factory()
                .create(context, coinRepositoryProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            coinsRepositoriesProvider: CoinRepositoriesProvider,
        ): AppComponent
    }

    fun inject(activity: ActivityMain)
}
