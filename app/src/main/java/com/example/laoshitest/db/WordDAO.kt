package com.example.laoshitest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laoshitest.data.wordData.WordItem

@Dao
interface WordDAO {
    @Query("SELECT * FROM word")
    fun getAllWords(): List<WordItem>

    @Query("SELECT * FROM word WHERE id IN (:ids)")
    fun getWordsByIds(ids: List<Int>): List<WordItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWords(books: List<WordItem>)

}