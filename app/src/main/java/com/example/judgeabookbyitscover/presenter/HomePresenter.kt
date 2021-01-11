package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.datamodels.Book

// extend BasePresenter and implement HomeContract.Presenter
class HomePresenter: BasePresenter<HomeContract.View>(), HomeContract.Presenter, BookRepository.OnFinishedListener {

    override fun onResponse(books: List<Book>) { view?.onResponse(books) }
    override fun onFailure(msg: String) { view?.onFailure(msg)}

    override fun getBooks() {
        // view?.ShowProgress()

        bookRepository?.getBookImgs(this)
    }


}