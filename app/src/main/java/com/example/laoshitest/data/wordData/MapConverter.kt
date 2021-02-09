package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import kotlinx.serialization.builtins.ListSerializer
import org.json.JSONArray
import java.util.stream.Collectors

class MapConverter {
        @TypeConverter
        fun fromMap(strings: Map<String, String>): String {
            return Json.encodeToString(strings)
        }

        @TypeConverter
        fun toMap(data: String): Map<String, String> {
            return Json.decodeFromString<Map<String, String>>(data)
        }

}