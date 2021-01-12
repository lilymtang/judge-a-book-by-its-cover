package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.datamodels.Book

/**
 * HomeContract implement BaseContract members to outline responsibilities of HomeActivity and HomePresenter
 */
interface DetailContract {
    interface View : BaseContract.View {

        // Callback functions that presenter will call to notify view
        fun onResponse(books: List<Book>)
        fun onFailure(msg: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun addToShelf(book: Book)
        fun removeFromShelf(book: Book)
    }
}