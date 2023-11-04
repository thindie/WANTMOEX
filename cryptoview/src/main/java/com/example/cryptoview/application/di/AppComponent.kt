package com.example.cryptoview.application.di

import android.content.Context
import com.example.core.network.apiservice.di.NetworkComponent
import com.example.core.network.apiservice.di.NetworkProvider
import com.example.cryptoview.activity.ActivityMain
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [NetworkProvider::class])
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val networkProvider: NetworkProvider = NetworkComponent.init()
            return DaggerAppComponent
                .factory()
                .create(context, networkProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            networkProvider: NetworkProvider,
        ): AppComponent
    }

    fun inject(activity: ActivityMain)
}
