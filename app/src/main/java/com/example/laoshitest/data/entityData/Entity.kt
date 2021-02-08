package com.example.laoshitest.data.entityData


import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity(
    val books: List<Book>,
    val collections: List<Collection>,
    val hsk: List<Hsk>
)

@JsonClass(generateAdapter = true)
data class Book(
    val children: List<Book>,
    val description: Map<String, String>,
    val id: Int,
    val image: Image?,
    val images: List<Image>,
    val index: Int?,
    val locales: List<String>,
    val style: Style?,
    val title: Map<String, String>,
    val type: String,
    val url: String?,
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int
)
@JsonClass(generateAdapter = true)
data class Collection(
    val children: List<CollectionItem>,
    val description: Map<String, String>,
    val id: Int,
    val style: Style?,
    val title: Map<String, String>,
    val type: String,
    val words: List<Int>?
)
@JsonClass(generateAdapter = true)
data class CollectionItem(
    val children: List<CollectionItem>,
    val description: Map<String, String>,
    val id: Int,
    val style: Style?,
    val title: Map<String, String>,
    val type: String,
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int
)
@JsonClass(generateAdapter = true)
data class Hsk(
    val children: List<HskItem>,
    val description: Map<String, String>,
    val id: Int,
    val style: Style?,
    val title: Map<String, String>,
    val type: String,
    val words: List<Int>?
)
@JsonClass(generateAdapter = true)
data class HskItem(
    val children: List<HskItem>,
    val description: Map<String, String>,
    val id: Int,
    val style: Style?,
    val title: Map<String, String>,
    val type: String,
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int
)
@JsonClass(generateAdapter = true)
data class Image(
    val preview: String,
    val thumbnail: String
)
@JsonClass(generateAdapter = true)
data class Style(
    @Json(name = "bottom_color")
    val bottomColor: String?,
    val shape: Int?,
    @Json(name = "top_color")
    val topColor: String?
)