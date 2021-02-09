package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import org.json.JSONArray
import java.util.stream.Collectors

class IntListConverter {
        @TypeConverter
        fun fromListOfStrings(strings: List<Int?>): String {
            return Json.encodeToString(strings)
        }

        @TypeConverter
        fun toListOfStrings(data: String): List<Int> {
            return Json.decodeFromString<List<Int>>(data)
        }

}