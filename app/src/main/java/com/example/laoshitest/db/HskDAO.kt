package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.FtsOptions.Order
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Hsk


@Dao
interface HskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHsk(hsk: Hsk)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHsk(hsks: List<Hsk>)

    @Query("SELECT * FROM hsk")
    fun getAllHsks(): List<Hsk>

}