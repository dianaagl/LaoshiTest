package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.example.laoshitest.data.entityData.Image

class ListImageConverter {
        @TypeConverter
        fun fromImage(img: List<Image>): String {
            var imgList = img.map {
                listOf<String>(it.preview, it.thumbnail)
            }
            return Json.encodeToString(imgList)
        }

        @TypeConverter
        fun toImage(data: String): List<Image> {
            val list = Json.decodeFromString<List<List<String>>>(data)
            return list.map {
                Image(it[0], it[1])
            }
        }
}