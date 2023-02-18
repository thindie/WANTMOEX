package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.CryptoCoinsRepositoryImpl
import com.example.thindie.wantmoex.data.CryptoNewsRepositoryImpl
import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.network.RemoteCoinRepositoryImpl
import com.example.thindie.wantmoex.data.storage.CryptoFavoritesRepositoryImpl
import com.example.thindie.wantmoex.data.storage.FavouriteCoinsRepository
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.data.storage.LocalCoinRepositoryImpl
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindEntityRepo(cryptoCoinsRepositoryImpl: CryptoCoinsRepositoryImpl): CryptoCoinRepository

    @Binds
    abstract fun bindFavoriteCoins(favoritesRepositoryImpl: CryptoFavoritesRepositoryImpl): FavouriteCoinsRepository

    @Binds
    abstract fun bindCryptoNewsRepo(cryptoNewsRepositoryImpl: CryptoNewsRepositoryImpl): CryptoNewsRepository

    @Binds
    abstract fun bindLocalRepository(local: LocalCoinRepositoryImpl): LocalCoinRepository

    @Binds
    abstract fun bindRemoteRepository(remote: RemoteCoinRepositoryImpl): RemoteCoinRepository

}
