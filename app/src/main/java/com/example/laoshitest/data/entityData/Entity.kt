package com.example.laoshitest.data.entityData


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.laoshitest.data.wordData.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity(
    val books: List<Book>,
    val collections: List<Collection>,
    val hsk: List<Hsk>
)

@Entity(tableName = "book")
@JsonClass(generateAdapter = true)
data class Book(
    @PrimaryKey
    val id: Int,

    @TypeConverters(MapConverter::class)
    val description: Map<String, String>,

    @TypeConverters(ImageConverter::class)
    val image: Image?,

    @TypeConverters(ListImageConverter::class)
    val images: List<Image?>,
    val index: Int?,

    @TypeConverters(StringListConverter::class)
    val locales: List<String>,

    @TypeConverters(StyleConverter::class)
    val style: Style?,
    @TypeConverters(MapConverter::class)
    val title: Map<String, String>,
    val type: String,
    val url: String?,
    @TypeConverters(IntListConverter::class)
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int,
    @Json(name = "parent_id")
    var parentId: Int = -1,
    @Ignore var children: List<Book>
) {
    constructor(
        id: Int,
        description: Map<String, String>,
        image: Image?,
        images: List<Image>,
        index: Int?,
        locales: List<String>,
        style: Style?,
        title: Map<String, String>,
        type: String,
        url: String?,
        words: List<Int>?,
        wordsCount: Int,
        parentId: Int
    )
            : this(
        id, description, image, images, index, locales, style, title, type, url, words,
        wordsCount, parentId, listOf()
    )
}
@Entity(tableName = "collection")
@JsonClass(generateAdapter = true)
data class Collection(
    @PrimaryKey
    val id: Int,
    @TypeConverters(MapConverter::class)
    val description: Map<String, String>,

    @TypeConverters(StyleConverter::class)
    val style: Style?,
    @TypeConverters(MapConverter::class)
    val title: Map<String, String>,
    val type: String,
    @TypeConverters(IntListConverter::class)
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int = 0,
    @Json(name = "category_id")
    var categoryId: Int = -1,
    @Ignore var children: List<Collection>
){
    constructor(id: Int, description: Map<String, String>, style: Style?,
                title: Map<String, String>, type: String, words: List<Int>?, wordsCount: Int, categoryId: Int)
            : this(id, description, style, title, type, words, wordsCount, categoryId, listOf())
}

@Entity(tableName = "hsk")
@JsonClass(generateAdapter = true)
data class Hsk(
    @PrimaryKey
    val id: Int,
    @TypeConverters(MapConverter::class)
    val description: Map<String, String>,
    @TypeConverters(StyleConverter::class)
    val style: Style?,
    @TypeConverters(MapConverter::class)
    val title: Map<String, String>,
    val type: String,
    @TypeConverters(IntListConverter::class)
    val words: List<Int>?,
    @Json(name = "words_count")
    val wordsCount: Int = 0,
    @Json(name = "hsk_id")
    var hskId: Int = -1,
    @Ignore var children: List<Hsk>,
){    constructor(id: Int, description: Map<String, String>, style: Style?,
                  title: Map<String, String>, type: String, words: List<Int>?, wordsCount: Int, hskId: Int)
        : this(id, description, style, title, type, words, wordsCount, hskId, listOf())
}
@TypeConverters(ImageConverter::class)
@JsonClass(generateAdapter = true)
data class Image(
    val preview: String,
    val thumbnail: String
)

@TypeConverters(StyleConverter::class)
@JsonClass(generateAdapter = true)
data class Style(
    @Json(name = "bottom_color")
    val bottomColor: String?,
    val shape: Int?,
    @Json(name = "top_color")
    val topColor: String?
)