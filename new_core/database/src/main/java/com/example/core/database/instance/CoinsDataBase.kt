package com.example.core.database.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [CoinDbModel::class], version = 1, exportSchema = false)
internal abstract class CoinsDataBase : RoomDatabase() {
    abstract fun getInstance(): CoinDao
}
