package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.db.Repository

interface BaseContract {
    interface View {

    }

    interface Presenter<V: View> {
        fun create(view: V, bookRepository: BookRepository, repository: Repository)
        fun destroy()
    }
}