package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.example.laoshitest.data.entityData.Image
import com.example.laoshitest.data.entityData.Style
import com.squareup.moshi.Moshi
import org.json.JSONArray
import java.util.stream.Collectors

class StyleConverter {
        @TypeConverter
        fun fromImage(style: Style?): String {
            return style?.let {
                "${style?.bottomColor ?: null}^${ style?.shape ?: 0 }^${style?.topColor ?: null}"
            } ?: "null"

        }

        @TypeConverter
        fun toImage(data: String): Style? {
            if(data != "null") {
                val array = data.split("^").toTypedArray()
                return Style(array.get(0), array.get(1).toInt(), array.get(2))
            }
            else return null
        }

}