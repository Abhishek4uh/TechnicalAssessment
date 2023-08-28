package com.example.myapplication.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface OfflineBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoFavs(favBookModel: FavBookModel)

    @Query(value = "Select * from watchlist order by id ASC")
    fun getWatchListBooks(): LiveData<List<FavBookModel>>

    @Query("DELETE FROM watchlist WHERE contentId = :contentId")
    suspend fun deleteFromWatchList(contentId: String)

}