package com.example.core.database.instance

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    /**
     * Retrieves a list of all ticker symbols.
     * @return A list of fromSymbol values.
     **/

    @Query("SELECT fromSymbol FROM coins")
    suspend fun getTickers(): List<String>

    /**
     * Observes all coins in the database, limited by the provided capacity.
     * [capacity] The maximum number of coins to observe.
     *@return A flow of lists of [CoinDbModel] entities.
     **/
    @Query("SELECT * FROM coins LIMIT :capacity")
    fun observeAllCoins(
        capacity: Int,
    ): Flow<List<CoinDbModel>>


    /**
     * Retrieves the CoinDbModel entity for the provided ticker symbol.
     * [ticker] The ticker symbol of the desired CoinDbModel.
     * @return The [CoinDbModel] entity with the provided ticker symbol.
     */
    @Query("SELECT * FROM coins WHERE fromSymbol == :ticker LIMIT 1")
    suspend fun getCoin(ticker: String): CoinDbModel

    /**
     * Inserts or updates a list of CoinDbModel entities into the database.
     * [list] The list of CoinDbModel entities to insert or update.
     */
    @Upsert
    suspend fun upsertList(list: List<CoinDbModel>)

    /**
     *  Inserts or updates a single CoinDbModel entity into the database.
     *  [coin] The CoinDbModel entity to insert or update
     */
    @Upsert
    suspend fun upsertCoin(coin: CoinDbModel)
}
