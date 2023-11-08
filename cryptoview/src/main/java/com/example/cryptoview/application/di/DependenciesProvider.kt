package com.example.cryptoview.application.di

import com.example.core.data.coinlist.di.CoinRepositoriesProvider
import com.example.core.data.uistatesettings.di.SettingsProvider
import com.example.core.network.apiservice.di.NetworkProvider
import com.example.core.presentation.uicoin.di.CoinFeatureProvider

interface DependenciesProvider :
    NetworkProvider,
    CoinRepositoriesProvider,
    SettingsProvider,
    CoinFeatureProvider
