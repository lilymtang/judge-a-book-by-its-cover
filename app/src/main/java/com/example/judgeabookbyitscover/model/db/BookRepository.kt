package com.example.judgeabookbyitscover.model.db

import com.example.judgeabookbyitscover.model.BooksApi
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.datamodels.NytResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

///**
// * Repository for fetching Books from the network
// */
//class BookRepository {
//    var service = BooksApi.BooksApiService
//
//    // Callback functions that repository will call to notify presenter
//    interface OnFinishedListener {
//        fun onResponse(books: List<Book>)
//        fun onFailure(msg: String)
//    }
//
//    fun getBookImgs(onFinishedListener: OnFinishedListener) {
//        lateinit var books : List<Book>
//
//        service.getListsOverview().enqueue(object : Callback<NytResponse> {
//
//            override fun onFailure(call: Call<NytResponse>, t: Throwable) {
//                val message: String = t.message ?: "Error happened"
//                t.printStackTrace()
//
//                onFinishedListener.onFailure(message)
//            }
//
//            override fun onResponse(call: Call<NytResponse>, response: Response<NytResponse>) {
//
//                // All bestsellers lists; List.books contains the bestsellers for that list
//                var lists = response.body()?.results?.lists
//
//                if (lists != null) {
//
//                    // Obtain distinct Books only, on 3 fields: image link, author, and title
//                    books = lists.flatMap { it.books }.map { it }.distinctBy{ it -> listOf(it.book_image, it.author, it.title) }
//
//                    onFinishedListener.onResponse(books)
//                }
//            }
//        })
//    }
//
//}
