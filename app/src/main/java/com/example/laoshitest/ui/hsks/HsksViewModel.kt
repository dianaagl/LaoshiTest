package com.example.laoshitest.ui.hsks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.data.entityData.Hsk
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HsksViewModel: ViewModel() {
    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    private val _collections = MutableLiveData<List<Hsk>>()
    val collections: LiveData<List<Hsk>> = _collections

    fun getAllCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mDatabase.hskDAO().getCategories().toMutableList()
            list.forEach {
                it.children = mDatabase.hskDAO().getCollectionsByCategory(it.id)
            }
            _collections.postValue(list)
    }
    }
}