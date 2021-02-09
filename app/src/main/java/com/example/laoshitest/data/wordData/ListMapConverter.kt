package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import kotlinx.serialization.builtins.ListSerializer
import org.json.JSONArray
import java.util.stream.Collectors

class ListMapConverter {
        @TypeConverter
        fun fromMap(strings: List<Map<String, String>>): String {
            return Json.encodeToString(strings)
        }

        @TypeConverter
        fun toMap(data: String): List<Map<String, String>> {
            return Json.decodeFromString<List<Map<String, String>>>(data)
        }

}