package com.example.judgeabookbyitscover.model.db

import androidx.lifecycle.LiveData
import com.example.judgeabookbyitscover.model.datamodels.Book


interface RoomRepository {
    suspend fun addBookToShelf(book: Book)
    suspend fun removeBookFromShelf(book: Book)
    suspend fun getBooksFromShelf(): List<Book>
}

class Repository(var db: BookDatabase) : RoomRepository {

    override suspend fun addBookToShelf(book: Book) {
        db.bookDao.insert(book)
    }

    override suspend fun removeBookFromShelf(book: Book) {
        db.bookDao.delete(book)
    }

    override suspend fun getBooksFromShelf():List<Book> {
        return db.bookDao.getBooks()
    }

}