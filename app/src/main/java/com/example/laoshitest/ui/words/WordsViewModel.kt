package com.example.laoshitest.ui.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.wordData.WordItem
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsViewModel: ViewModel() {

    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    private val _words = MutableLiveData<List<WordItem>>()
    val words: LiveData<List<WordItem>> = _words

    fun initData(id: Int, type: String){
        viewModelScope.launch(Dispatchers.IO) {
            when(type){
                "book" -> {
                    val book = mDatabase.bookDAO().getBookById(id)
                    book?.let { val words = book.words
                        words?.let { _words.postValue(mDatabase.wordDAO().getWordsByIds(words)) }
                    }
                }
                "hsk" -> {
                    val hsk = mDatabase.hskDAO().getHskById(id)
                    hsk?.let { val words = hsk.words
                        words?.let { _words.postValue(mDatabase.wordDAO().getWordsByIds(words)) }
                    }
                }
                "collection" -> {
                    val collection = mDatabase.collectionDAO().getCollectionById(id)
                    collection?.let { val words = it.words
                        words?.let { it2 -> _words.postValue(mDatabase.wordDAO().getWordsByIds(it2)) }
                    }
                }
            }
        }
    }
}