package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.Repository

abstract class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null
    protected var repository: Repository? = null

    override fun create(view: V, repository: Repository) {
        this.view = view
        this.repository = repository
    }

    override fun destroy() {
        this.view = null
    }
}