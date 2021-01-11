package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.Repository
import com.example.judgeabookbyitscover.model.datamodels.Book

// extend BasePresenter and implement HomeContract.Presenter
class HomePresenter: BasePresenter<HomeContract.View>(), HomeContract.Presenter, Repository.OnFinishedListener {
    override fun onSuccess(books: List<Book>) {
        view?.onGetDataSuccess(books)

    }

    override fun onFail(msg: String) {
        view?.onGetDataError(msg)
    }

    /**
     * TODO: presenter calls model
     */
    override fun getBooks() {
        // view?.ShowProgress()

        repository?.getBookImgs(this)
    }


}