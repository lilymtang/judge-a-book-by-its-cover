package com.example.judgeabookbyitscover.model.db

import androidx.room.*
import com.example.judgeabookbyitscover.model.datamodels.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Delete
    fun delete(vararg book: Book)

    @Query("SELECT * FROM bookshelf")
    fun getBooks(): List<Book>
}