package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Hsk

@Dao
interface BookDAO {
    @Query("SELECT * FROM book")
    fun getAllBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(books: List<Book>)
}