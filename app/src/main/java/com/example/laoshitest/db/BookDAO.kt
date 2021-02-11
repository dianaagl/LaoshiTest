package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.entityData.Book

@Dao
interface BookDAO {
    @Query("SELECT * FROM book")
    fun getAllBooks(): List<Book>

    @Query("SELECT * FROM book WHERE id=:id")
    fun getBookById(id: Int): Book?

    @Query("SELECT * FROM book WHERE type='series'")
    fun getBookSeries(): List<Book>

    @Query("SELECT * FROM book WHERE type='book' AND parentId=:id")
    fun getBooksFromSerie(id: Int): List<Book>

    @Query("SELECT * FROM book WHERE type='lesson' AND parentId=:id")
    fun getLessonsFromBook(id: Int): List<Book>

    @Query("SELECT * FROM book WHERE parentId=:id")
    fun getBookChildren(id: Int): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(books: List<Book>)
}