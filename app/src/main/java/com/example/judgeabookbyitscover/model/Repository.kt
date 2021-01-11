package com.example.judgeabookbyitscover.model

import android.util.Log
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.datamodels.ListsOverview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository for fetching Books from the network
 */
class Repository {
    var service = BooksApi.BooksApiService

    interface OnFinishedListener {
        fun onSuccess(data: List<Book>)
        fun onFail(msg: String)
    }

    fun getBookImgs(onFinishedListener: OnFinishedListener) {
        lateinit var books : List<Book>

        service.getListsOverview().enqueue(object : Callback<ListsOverview> {

            override fun onFailure(call: Call<ListsOverview>, t: Throwable) {
                val message: String = t.message ?: "Error happened"
                Log.d("RESPONSE_", message)
                Log.d("RESPONSE_", call.request().url().toString())
                t.printStackTrace()

                onFinishedListener.onFail(message)
            }

            override fun onResponse(call: Call<ListsOverview>, response: Response<ListsOverview>) {
                Log.d("RESPONSE_", call.request().url().toString())

                var lists = response.body()?.results?.lists
                if (lists != null) {

                    // Distinct Book objects
                    books = lists.flatMap { it.books }.map { it }.distinctBy{ it -> it.book_image }

                    onFinishedListener.onSuccess(books)
                }
            }
        })
    }

}