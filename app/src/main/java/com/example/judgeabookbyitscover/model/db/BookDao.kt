package com.example.judgeabookbyitscover.model.db

import androidx.room.*
import com.example.judgeabookbyitscover.model.datamodels.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(vararg book: Book)

    @Query("SELECT * FROM bookshelf")
    suspend fun getBooks(): List<Book>

    @Query("SELECT * FROM bookshelf where book_image = :img")
    suspend fun deleteBookByImage(img: String): List<Book>
}