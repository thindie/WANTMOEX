package com.example.cryptoview.application.di

import com.example.core.data.coinlist.di.CoinRepositoriesProvider
import com.example.core.data.uistatesettings.di.SettingsProvider

interface DependenciesProvider : CoinRepositoriesProvider, SettingsProvider
