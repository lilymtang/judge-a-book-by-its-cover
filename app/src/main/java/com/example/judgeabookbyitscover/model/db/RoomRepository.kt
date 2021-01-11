package com.example.judgeabookbyitscover.model.db

import androidx.lifecycle.LiveData
import com.example.judgeabookbyitscover.model.datamodels.Book


interface RoomRepository {
    fun addBookToShelf(book: Book)
    fun removeBookFromShelf(isbn: String)
    fun getBooksFromShelf(): LiveData<List<Book>>
}
//
//class Repository() : RoomRepository {
//
//}