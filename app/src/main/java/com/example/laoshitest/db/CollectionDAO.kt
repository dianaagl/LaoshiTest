package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.entityData.Collection

@Dao
interface CollectionDAO {
    @Query("SELECT * FROM collection")
    fun getAllCollections(): List<Collection>

    @Query("SELECT * FROM collection WHERE categoryId=:id")
    fun getCollectionsByCategory(id: Int): List<Collection>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCollections(books: List<Collection>)

    @Query("SELECT * FROM collection WHERE type='category'")
    fun getCategories(): List<Collection>

    @Query("SELECT * FROM collection WHERE id=:id")
    fun getCollectionById(id: Int): Collection?

}