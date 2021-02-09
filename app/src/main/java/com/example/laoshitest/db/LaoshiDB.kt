package com.example.laoshitest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Collection
import com.example.laoshitest.data.entityData.Entity
import com.example.laoshitest.data.entityData.Hsk
import com.example.laoshitest.data.wordData.*


@Database(
    entities = arrayOf(WordItem::class, Book::class, Collection::class, Hsk::class),
    version = 1
)
@TypeConverters(
    StringListConverter::class, ListMapConverter::class, MapConverter::class,
    IntListConverter::class, ImageConverter::class, ListImageConverter::class, StyleConverter::class
)
abstract class LaoshiDB : RoomDatabase() {
    abstract fun wordDAO(): WordDAO
    abstract fun bookDAO(): BookDAO
    abstract fun collectionDAO(): CollectionDAO
    abstract fun hskDAO(): HskDAO

    companion object DatabaseFact {
        @Volatile
        private var INSTANCE: LaoshiDB? = null

        fun getDatabase(context: Context): LaoshiDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LaoshiDB::class.java,
                    "laoshi_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}