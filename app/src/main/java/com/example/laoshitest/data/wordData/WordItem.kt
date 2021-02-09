package com.example.laoshitest.data.wordData

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass

@Entity(tableName = "word")
@JsonClass(generateAdapter = true)
data class WordItem(
    @PrimaryKey
    val id: Int,
    @TypeConverters(ListMapConverter::class)
    val categories: List<Map<String, String>>,

    @TypeConverters(StringListConverter::class)
    val transcriptions: List<String>,

    @TypeConverters(MapConverter::class)
    val translations: Map<String, String>,
    val word: String
)
