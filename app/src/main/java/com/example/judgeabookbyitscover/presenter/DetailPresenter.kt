package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.datamodels.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// extend BasePresenter and implement HomeContract.Presenter
class DetailPresenter: BasePresenter<DetailContract.View>(), DetailContract.Presenter {

//    private var job: Job = Job()
//    private val scope = CoroutineScope(job + Dispatchers.Main)
//
//    fun detachView() {
//        // cancel the job when view is detached
//        job.cancel()
//    }

    override fun addToShelf(book: Book) {
//        scope.launch{
//            repository!!.addBookToShelf(book)
//        }
    }
}