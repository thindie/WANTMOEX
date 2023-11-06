package com.example.core.data.uistatesettings.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SettingsRepositoryModule::class])
interface SettingsComponent : SettingsProvider {
    companion object {
        fun init(): SettingsComponent {
            return DaggerSettingsComponent
                .factory()
                .create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): SettingsComponent
    }
}
