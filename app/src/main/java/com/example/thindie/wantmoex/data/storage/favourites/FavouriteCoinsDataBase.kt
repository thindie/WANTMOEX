package com.example.thindie.wantmoex.data.storage.favourites

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [FavouriteCoinDBModel::class], version = 1, exportSchema = false)
abstract class FavouriteCoinsDataBase : RoomDatabase() {


    abstract fun coinFavouriteListDao(): FavouriteCoinDao

    companion object {
        private var INSTANCE: FavouriteCoinsDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "favourite_coins.db"

        fun getInstance(application: Application): FavouriteCoinsDataBase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                FavouriteCoinsDataBase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }

}