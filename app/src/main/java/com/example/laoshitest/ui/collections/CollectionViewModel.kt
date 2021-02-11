package com.example.laoshitest.ui.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.data.entityData.Collection
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionViewModel: ViewModel() {
    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    private val _collections = MutableLiveData<List<Collection>>()
    val collections: LiveData<List<Collection>> = _collections

    fun getAllCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mDatabase.collectionDAO().getCategories().toMutableList()
            list.forEach {
                it.children = mDatabase.collectionDAO().getCollectionsByCategory(it.id)
            }
            _collections.postValue(list)
    }
    }
}