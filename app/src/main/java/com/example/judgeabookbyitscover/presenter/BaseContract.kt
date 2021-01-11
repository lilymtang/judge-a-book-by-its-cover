package com.example.judgeabookbyitscover.presenter

import com.example.judgeabookbyitscover.model.Repository

interface BaseContract {
    interface View {

    }

    interface Presenter<V: View> {
        fun create(view: V, repository: Repository)
        fun destroy()
    }
}