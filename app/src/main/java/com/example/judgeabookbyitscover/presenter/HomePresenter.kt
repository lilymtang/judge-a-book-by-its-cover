package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.Repository

// extend BasePresenter and implement HomeContract.Presenter
class HomePresenter: BasePresenter<HomeContract.View>(), HomeContract.Presenter, Repository.OnFinishedListener {
    override fun onSuccess(data: List<String>) {
        view?.onGetDataSuccess(data)
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