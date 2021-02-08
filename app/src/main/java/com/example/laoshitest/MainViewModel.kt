package com.example.laoshitest

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.laoshitest.data.wordData.WordItem
import com.example.laoshitest.data.entityData.Entity
import com.example.laoshitest.utils.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

import java.lang.reflect.Type

class MainViewModel: ViewModel() {
    var type: Type = Types.newParameterizedType(
        List::class.java,
        WordItem::class.java
    )
    fun initData(context: Context){
        val text = FileHelper.getJsonFromRaw(context, R.raw.words)
        parseWords(text ?: "")
        val text2 = FileHelper.getJsonFromRaw(context, R.raw.entity)
        parseEntities(text2)
    }

    fun parseWords(text: String) {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<WordItem>> = moshi.adapter(type)
        val data = adapter.fromJson(text)
        for(word in data ?: emptyList()){
            //Log.d("debuglaoshi", word.word)
        }
    }

    fun parseEntities(text: String) {
        val moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<Entity> = moshi.adapter(Entity::class.java)
        val data = adapter.fromJson(text)
    }
}