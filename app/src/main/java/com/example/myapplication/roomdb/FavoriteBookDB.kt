package com.example.myapplication.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavBookModel::class], version = 1, exportSchema = false)
abstract class FavoriteBookDB: RoomDatabase() {

    abstract fun getItemDao(): OfflineBookDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteBookDB? = null
        fun getDatabase(context: Context): FavoriteBookDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, FavoriteBookDB::class.java, "favorite_db").build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}