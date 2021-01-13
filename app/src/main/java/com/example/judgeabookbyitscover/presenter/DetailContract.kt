package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.datamodels.Book

/**
 * HomeContract implement BaseContract members to outline responsibilities of HomeActivity and HomePresenter
 */
interface DetailContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun addToShelf(book: Book)
        fun removeFromShelf(book: Book)
    }
}