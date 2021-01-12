package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.db.Repository

abstract class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null
    protected var bookRepository: BookRepository? = null
    protected var repository: Repository? = null

    override fun create(view: V, bookRepository: BookRepository, repository: Repository) {
        this.view = view
        this.bookRepository = bookRepository
        this.repository = repository
    }

    override fun destroy() {
        this.view = null
    }
}