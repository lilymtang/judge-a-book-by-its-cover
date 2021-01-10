package com.example.judgeabookbyitscover.presenter

// HomeContract implement BaseContract members
// Outlines responsibilities of HomeActivity (View) and HomePresenter
interface HomeContract {
    interface View : BaseContract.View {
        fun onGetDataSuccess(books: List<String>)
        fun onGetDataError(msg: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getBooks()
    }
}