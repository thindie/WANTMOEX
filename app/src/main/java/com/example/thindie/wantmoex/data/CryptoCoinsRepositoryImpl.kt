package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.fromDBtoCoin
import com.example.thindie.wantmoex.data.mappers.fromDTOtoCoinDBModel
import com.example.thindie.wantmoex.data.network.dto.fromMultiFullToDTO
import com.example.thindie.wantmoex.data.network.dto.fromTotalVolFullToDTOList
import com.example.thindie.wantmoex.data.network.dto.multifull.CoinRawMultiFullResponseDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinRawTotalVolFullResponseDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.data.storage.AppDataBase
import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDBModel
import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinsDataBase
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val cryptoCoinsApiService: CryptoCoinsApiService,
    private val appDataBase: AppDataBase,
    private val favouriteCoinsDataBase: FavouriteCoinsDataBase
) : CryptoCoinRepository, FavouriteCoinsRepository {


    override suspend fun getAll(): Flow<List<Coin>> {
        var resultList: List<Coin>? = null
        var rawNetWorkData: CoinRawTotalVolFullResponseDTO? = null
        try {
            rawNetWorkData = cryptoCoinsApiService.getTopCoins(limit = LIMIT)
        } catch (e: Exception) {
            resultList = appDataBase.coinListDao().getAllCoins().map { fromDBtoCoin(it) }
        }
        if (resultList.isNullOrEmpty()) {
            val listDTO = rawNetWorkData?.let { fromTotalVolFullToDTOList(it) }
            if (listDTO != null) {
                appDataBase.coinListDao().insertPriceList(
                    listDTO.map {
                        fromDTOtoCoinDBModel(it)
                    }
                )
                return flow {
                    emit(
                        appDataBase.coinListDao().getAllCoins().map { fromDBtoCoin(it) }
                    )
                }
            }
        }
        return flow {
            if (resultList != null) {
                emit(resultList)
            }
        }
    }


    override suspend fun getSingle(fromSymbol: String): Coin {
        var resultList: List<Coin>? = null
        var rawNetWorkData: CoinRawMultiFullResponseDTO? = null
        try {
            rawNetWorkData = cryptoCoinsApiService.getCoin(fSyms = fromSymbol)
        } catch (e: Exception) {
            resultList = listOf(appDataBase.coinListDao().getCoin(fromSymbol)).map {
                fromDBtoCoin(it)
            }
        }

        if (resultList.isNullOrEmpty()) {
            val listDTO = rawNetWorkData?.let { fromMultiFullToDTO(it) }
            if (listDTO != null && listDTO.isNotEmpty()) {
                val listDBModel = listDTO.map {
                    fromDTOtoCoinDBModel(it)
                }
                val listCoin = listDBModel.map {
                    fromDBtoCoin(it)
                }
                return listCoin[INDEX]
            }
            try {
                resultList = listOf(appDataBase.coinListDao().getCoin(fromSymbol)).map {
                    fromDBtoCoin(it)
                }
            } catch (e: Exception) {
                resultList = appDataBase.coinListDao().getAllCoins().map { fromDBtoCoin(it) }
            }

        }

        return resultList?.get(INDEX) ?: throw Exception(SERIOUS_EXCEPTION)
    }


    override suspend fun getAllFavoriteCoins(): Flow<List<Coin>> {
        val listOfCoinNames = favouriteCoinsDataBase.coinFavouriteListDao().getAllFavouriteCoins()
            .map { it.fromSymbol }
        return flow {
            getAll().collect {
                val filteredList = it.filter { coin ->
                    listOfCoinNames.contains(coin.fromSymbol)
                }
                emit(filteredList)
            }
        }
    }

    override suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>) {
        val listOfID = favouriteCoinsDataBase.coinFavouriteListDao().getAllFavouriteCoins()
        listOfID.forEach {
            if (deleteCoins.contains(it.fromSymbol)) {
                favouriteCoinsDataBase.coinFavouriteListDao().deleteFavouriteCoin(it.id)
            }
        }

    }

    override suspend fun addToFavoriteCoins(addCoins: List<String>) {
        addCoins.forEach {
            favouriteCoinsDataBase.coinFavouriteListDao()
                .insertFavouriteCoin(FavouriteCoinDBModel(fromSymbol = it, id = 0))
        }
    }

    companion object {
        private const val LIMIT = 30
        private const val INDEX = 0
        private const val SERIOUS_EXCEPTION = "it cannot happen obviously"
    }
}