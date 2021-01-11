package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository

abstract class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null
    protected var bookRepository: BookRepository? = null

    override fun create(view: V, bookRepository: BookRepository) {
        this.view = view
        this.bookRepository = bookRepository
    }

    override fun destroy() {
        this.view = null
    }
}