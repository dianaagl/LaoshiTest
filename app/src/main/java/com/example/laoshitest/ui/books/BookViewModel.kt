package com.example.laoshitest.ui.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {
    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    private val _serie = MutableLiveData<Book>()
    val serie: LiveData<Book> = _serie

    fun getSerie(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val list = mDatabase.bookDAO().getBookChildren(id)
            val serieItem = mDatabase.bookDAO().getBookById(id)
            serieItem?.children = list
            _serie.postValue(serieItem)
        }
    }
}