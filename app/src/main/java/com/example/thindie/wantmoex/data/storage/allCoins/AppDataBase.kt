package com.example.thindie.wantmoex.data.storage.allCoins

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [CoinDBModel::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun coinListDao(): CoinDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "coinsDataBase.db"

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                AppDataBase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
            INSTANCE = db
            return db
        }
    }

}