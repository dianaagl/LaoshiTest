package com.example.laoshitest.ui.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.data.wordData.WordItem
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsViewModel: ViewModel() {

    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    private val _words = MutableLiveData<List<WordItem>>()
    val words: LiveData<List<WordItem>> = _words

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    fun initData(id: Int, type: String, lng: String){
        viewModelScope.launch(Dispatchers.IO) {
            when(type){
                "book" -> {
                    val book = mDatabase.bookDAO().getBookById(id)
                    book?.let {
                        _title.postValue(it.title[lng])
                        val words = book.words
                        words?.let { _words.postValue(mDatabase.wordDAO().getWordsByIds(words)) }
                    }
                }
                "hsk" -> {
                    val hsk = mDatabase.hskDAO().getHskById(id)
                    hsk?.let {
                        _title.postValue(it.title[lng])
                        val words = hsk.words
                        words?.let { _words.postValue(mDatabase.wordDAO().getWordsByIds(words)) }
                    }
                }
                "collection" -> {
                    val collection = mDatabase.collectionDAO().getCollectionById(id)
                    collection?.let {
                        _title.postValue(it.title[lng])
                        val words = it.words
                        words?.let { it2 -> _words.postValue(mDatabase.wordDAO().getWordsByIds(it2)) }
                    }
                }
            }
        }
    }
}