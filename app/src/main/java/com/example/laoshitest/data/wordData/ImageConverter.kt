package com.example.laoshitest.data.wordData
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

import androidx.room.TypeConverter
import com.example.laoshitest.data.entityData.Image
import com.squareup.moshi.Moshi
import org.json.JSONArray
import java.util.stream.Collectors

class ImageConverter {
        @TypeConverter
        fun fromImage(img: Image?): String {
            return img?.let { "${img.preview}^${img.thumbnail}" } ?: "null"
        }

        @TypeConverter
        fun toImage(data: String): Image? {
            if(data == "null"){
                return null
            }
            val array = data.split("^")
            return Image(array.get(0),array.get(1))
        }

}