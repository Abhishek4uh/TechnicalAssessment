package com.example.myapplication.roomdb

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteBookViewModel(application: Application) : AndroidViewModel(application) {


    var allBooks : LiveData<List<FavBookModel>>
    private var repository: FavoriteBookRepository

    init {
        val dao=FavoriteBookDB.getDatabase(application).getItemDao()
        repository= FavoriteBookRepository(dao)
        allBooks=repository.getAllNote()
    }

    //Deleting...
    fun deleteItem(contentId:String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.delete(contentId)
        } catch (e: Exception) {
            Log.d("FavoriteBookViewModel", "Error deleting item from database: ${e.message}")
        }
    }

    //Inserting...
    fun addItem(favBookModel: FavBookModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favBookModel)
    }


}