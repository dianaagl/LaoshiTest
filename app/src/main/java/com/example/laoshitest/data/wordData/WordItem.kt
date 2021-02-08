package com.example.laoshitest.data.wordData

import androidx.room.Entity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordItem(
    val categories: List<Map<String, String>>,
    val id: Int,
    val transcriptions: List<String>,
    val translations: Map<String, String>,
    val word: String
)
