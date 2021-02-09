package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import org.json.JSONArray
import java.util.stream.Collectors

class StringListConverter {
        @TypeConverter
        fun fromListOfStrings(strings: List<String?>): String {
            return Json.encodeToString(strings)
        }

        @TypeConverter
        fun toListOfStrings(data: String): List<String> {
            return Json.decodeFromString<List<String>>(data)
        }

}