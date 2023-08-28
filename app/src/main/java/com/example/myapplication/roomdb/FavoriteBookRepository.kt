package com.example.myapplication.roomdb

import androidx.lifecycle.LiveData

class FavoriteBookRepository(private var offlineBookDao: OfflineBookDao) {

    //inserting..
    suspend fun insert(favBookModel: FavBookModel){
        offlineBookDao.insertIntoFavs(favBookModel)
    }

    //fetching..
    fun getAllNote(): LiveData<List<FavBookModel>> {
        return offlineBookDao.getWatchListBooks()
    }

    //deleting..
    suspend fun delete(contentId:String){
        offlineBookDao.deleteFromWatchList(contentId)
    }

}