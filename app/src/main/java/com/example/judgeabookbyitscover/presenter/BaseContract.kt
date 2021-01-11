package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.db.BookRepository

interface BaseContract {
    interface View {

    }

    interface Presenter<V: View> {
        fun create(view: V, bookRepository: BookRepository)
        fun destroy()
    }
}