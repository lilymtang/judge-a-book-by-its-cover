package com.example.judgeabookbyitscover.model.db

import androidx.room.*
import com.example.judgeabookbyitscover.model.datamodels.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(vararg book: Book)

    @Query("SELECT * FROM bookshelf")
    suspend fun getBooks(): List<Book>
}