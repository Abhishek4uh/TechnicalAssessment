package com.example.myapplication.callback

import com.example.myapplication.roomdb.FavBookModel

interface AddToFavorite {
    fun addedToFavorite(favBookModel: FavBookModel,isChecked: Boolean)
}