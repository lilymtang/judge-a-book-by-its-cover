package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.datamodels.Book

// HomeContract implement BaseContract members
// Outlines responsibilities of HomeActivity (View) and HomePresenter
interface HomeContract {
    interface View : BaseContract.View {
        fun onGetDataSuccess(books: List<Book>)
        fun onGetDataError(msg: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getBooks()
    }
}