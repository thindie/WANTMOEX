package com.example.thindie.wantmoex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @IODispatcher
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IODispatcher
}

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

    @Singleton
    @Provides
    fun providesCoroutineScope(
        @DispatchersModule.IODispatcher dispatchersIO: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatchersIO)
}
