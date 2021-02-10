package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.FtsOptions.Order
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Collection
import com.example.laoshitest.data.entityData.Hsk


@Dao
interface HskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHsk(hsk: Hsk)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHsk(hsks: List<Hsk>)

    @Query("SELECT * FROM hsk")
    fun getAllHsks(): List<Hsk>

    @Query("SELECT * FROM hsk WHERE hskId=:id")
    fun getCollectionsByCategory(id: Int): List<Hsk>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCollections(hsks: List<Hsk>)

    @Query("SELECT * FROM hsk WHERE type='level'")
    fun getCategories(): List<Hsk>

    @Query("SELECT * FROM hsk WHERE id=:id")
    fun getHskById(id: Int): Hsk?
}