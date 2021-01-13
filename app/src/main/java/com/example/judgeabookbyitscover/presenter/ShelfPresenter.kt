package com.example.judgeabookbyitscover.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// extend BasePresenter and implement ShelfContract.Presenter
class ShelfPresenter : BasePresenter<ShelfContract.View>(), ShelfContract.Presenter {
    private var job: Job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    fun detachView() {
        // cancel the job when view is detached
        job.cancel()
    }

    override fun getShelfBooks() {
        scope.launch {
            val shelfBooks = repository!!.getBooksFromShelf()
            view!!.onResponse(shelfBooks)
        }
    }
}