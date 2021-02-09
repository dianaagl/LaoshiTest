package com.example.laoshitest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.laoshitest.data.wordData.WordItem
import com.example.laoshitest.data.entityData.Entity
import com.example.laoshitest.utils.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.laoshitest.LaoshiApp
import com.example.laoshitest.R
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Collection
import com.example.laoshitest.data.entityData.Hsk
import com.example.laoshitest.db.LaoshiDB
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.lang.reflect.Type

class MainViewModel: ViewModel() {
    private val mDatabase: LaoshiDB = LaoshiDB.getDatabase(LaoshiApp.applicationContext())

    var type: Type = Types.newParameterizedType(
        List::class.java,
        WordItem::class.java
    )
    fun initData(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val text = FileHelper.getJsonFromRaw(context, R.raw.words)
            parseWords(text ?: "")
            val text2 = FileHelper.getJsonFromRaw(context, R.raw.entity)
            val entity = parseEntities(text2)
            entity?.let {
                mDatabase.hskDAO().insertAllHsk(it.hsk)
                for (hsk in it.hsk) {
                    insertHskChildren(hsk.children, hsk.id)
                }
                mDatabase.bookDAO().insertAllBooks(it.books)
                it.books.forEach {
                    insertBookChildren(it.children, it.id)
                }
                mDatabase.collectionDAO().insertAllCollections(it.collections)
                it.collections.forEach {
                     insertCollectionChildren(it.children, it.id)
                }
            }

        }
    }
    fun insertHskChildren(children: List<Hsk>, id: Int) {
        mDatabase.hskDAO().insertAllHsk(children.map {
            it.hskId = id
            insertHskChildren(it.children, it.id)
            it
        })
    }

    fun insertBookChildren(children: List<Book>, id: Int){
        mDatabase.bookDAO().insertAllBooks(
        children.map {
            it.parentId = id
            insertBookChildren(it.children, it.id)
            it
        })
    }

    fun insertCollectionChildren(children: List<Collection>, id: Int){
        mDatabase.collectionDAO().insertAllCollections(
        children.map {
            it.categoryId = id
            insertCollectionChildren(it.children, it.id)
            it
        })
    }

    fun parseWords(text: String): List<WordItem> {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<WordItem>> = moshi.adapter(type)
        return adapter.fromJson(text) ?: listOf()
    }

    fun parseEntities(text: String): Entity? {
        val moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<Entity> = moshi.adapter(Entity::class.java)
        val data = adapter.fromJson(text)
        return data
    }

    suspend fun getCategories(): List<Int>{
        return withContext(Dispatchers.IO) {
            val categories = mDatabase.collectionDAO().getCategories()
            categories.map { it.id }
        }
    }
}