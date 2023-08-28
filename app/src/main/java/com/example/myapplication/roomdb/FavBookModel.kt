package com.example.myapplication.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist")
data class FavBookModel(
    @ColumnInfo(name = "contentId") var contentId:String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "hits") var hits: String?,
    @ColumnInfo(name = "poster") var poster: String?) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
