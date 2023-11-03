package com.example.cryptoview.application

import android.app.Application
import com.example.cryptoview.application.di.AppComponent

class CryptoViewApplication : Application(), App {

    private lateinit var appComponent: AppComponent

    private fun isComponentInitialised() = ::appComponent.isInitialized

    override fun getAppComponent(): AppComponent {
        if (isComponentInitialised().not()) appComponent = AppComponent.init(applicationContext)
        return appComponent
    }
}
